### 參考https://medium.com/k8s%E7%AD%86%E8%A8%98/kubernetes-k8s-service%E5%AF%A6%E7%8F%BE%E8%97%8D%E7%B6%A0%E7%99%BC%E5%B8%83-kubernetes-blue-green-deployment-80d021152d05


## 藍綠布署
### 建立v1.0.0版本
#### 建立pod
kubectl apply -f .\deployment_training\blue-green-deployment\deployment_pod_1.0.0.yaml

kubectl get pod -l app=petclinic 

#### 建立Service

kubectl apply -f .\deployment_training\blue-green-deployment\petclinic-service.yaml


#### 測試
minikube service petclinic --url


