## 
### 建立pv
kubectl apply -f .\minikube_volume\pv.yaml 
### 建立pvc
kubectl apply -f .\minikube_volume\pvc.yaml  
### 建立pod
kubectl apply -f .\minikube_volume\pv-pod.yaml 
#### or
kubectl apply -f https://k8s.io/examples/pods/storage/pv-pod.yaml

### exec進pod中
kubectl exec -it task-pv-pod -- /bin/bash
#### prompt 執行
apt update
apt install curl

cd /usr/share/nginx/html
echo '<html style="background-color: green;"><h1>Success</h1></html>' > index.html

curl http://localhost/






