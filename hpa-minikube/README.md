# minikube_hpa
minikube_hpa


## 建立專案namespace
kubectl create namespace hpa   
### 建立yaml檔
ADD php-apache.yml

### 布署php-apache應用
#### 執行指令
kubectl apply -f .\php-apache.yaml
#### 確認
kubectl get deploy php-apache -n hpa 

```
NAME         READY   UP-TO-DATE   AVAILABLE   AGE
php-apache   1/1     1            1           73s
```

## 布署HPA
### 首先啟動metrics-server
minikube addons enable metrics-server

```
💡  metrics-server is an addon maintained by Kubernetes. For any concerns contact minikube on GitHub.
You can view the list of minikube maintainers at: https://github.com/kubernetes/minikube/blob/master/OWNERS   
    ▪ Using image registry.k8s.io/metrics-server/metrics-server:v0.6.4
🌟  The 'metrics-server' addon is enabled
```

### 建立HPA設定yaml
#### 建檔
php-apache-hpa.yml
#### 布署
kubectl apply -f php-apache-hpa.yml
#### 查看
kubectl get hpa -n hpa

```
NAME             REFERENCE               TARGETS   MINPODS   MAXPODS   REPLICAS   AGE
php-apache-hpa   Deployment/php-apache   0%/50%    1         10        1          17s

```
#### 若沒有安裝metrics-server TARGET會出現unknown

## 開始測試 增加負載
#### 啟動另一個container進行壓力測試
kubectl run -it --rm load-generator --image=busybox /bin/sh -n hpa
or
kubectl run -i --tty load-generator --rm --image=busybox --restart=Never -- /bin/sh -c "while sleep 1; do wget -q -O- http://php-apache; done" -n hpa
#### prompt輸入
```
/ # while sleep 1; do wget -q -O- http://php-apache; done
OK!OK!OK!OK!O
```

#### 再確認
kubectl get hpa -n hpa

```
NAME             REFERENCE               TARGETS   MINPODS   MAXPODS   REPLICAS   AGE
php-apache-hpa   Deployment/php-apache   18%/50%   1         10        1          10m
```
```
NAME             REFERENCE               TARGETS         MINPODS   MAXPODS   REPLICAS   AGE
php-apache-hpa   Deployment/php-apache   <unknown>/30%   1         10        0          8s
php-apache-hpa   Deployment/php-apache   0%/30%          1         10        1          15s
php-apache-hpa   Deployment/php-apache   5%/30%          1         10        1          45s
php-apache-hpa   Deployment/php-apache   20%/30%         1         10        1          105s
php-apache-hpa   Deployment/php-apache   19%/30%         1         10        1          2m45s
php-apache-hpa   Deployment/php-apache   58%/30%         1         10        1          3m45s
php-apache-hpa   Deployment/php-apache   58%/30%         1         10        2          4m
```
#### 開始擴展
```
NAME                          READY   STATUS    RESTARTS   AGE
load-generator                1/1     Running   0          32m
php-apache-69f9bc5fd5-q2jh9   1/1     Running   0          7s
php-apache-69f9bc5fd5-zssgs   1/1     Running   0          39m
```