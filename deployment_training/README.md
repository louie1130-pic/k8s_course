## 藍綠布署
### 建立v1.0.0版本
#### 建立pod
kubectl apply -f .\deployment_training\blue-green-deployment\deployment_pod_1.0.0.yaml

kubectl get pod -l app=petclinic 

#### 建立Service

kubectl apply -f .\deployment_training\blue-green-deployment\petclinic-service.yaml


#### 測試
minikube service petclinic --url


