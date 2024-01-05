# Prerequiest
## minikube
minikube version: v1.31.2


## vs code
Version: 1.85.0 (user setup)


# 一般打包手動部屬

## 建立image

### 打包
mvn clean install

### 本機unit test
java -jar .\target\spring-boot-docker-0.0.1-SNAPSHOT.jar


### 透過Dockerfile建立image
minikube image build -t spring-hellowworld-image:v1.2 -f ./Dockerfile .
minikube image build -t spring-hellowworld-image-ms:v1.0 -f ./Dockerfile_m .

## 建立deployment
kubectl apply -f .\deployment.yaml 


## 建立Service for test

### 使用NodePort去建立service並連接container port
kubectl expose deployment spring-boot-app --type=NodePort --port=8080

### 查詢本機(host) url和port
minikube service spring-boot-app --url


## 看log
 kubectl logs -l app=spring-boot-app --all-containers


# 匯出imge檔方式部屬

### 修改檔案

### 打包
mvn clean install

### 本機unit test
java -jar .\target\spring-boot-docker-0.0.1-SNAPSHOT.jar

### 透過Podman建立image
podman build -t spring-hellowworld-image:v1.3 -f ./Dockerfile .
podman build -t docker.io/library/spring-hellowworld-image:v1.4 .

### podman匯出tar檔
podman save > spring-hellowworld-image1_3.tar localhost/spring-hellowworld-image:v1.3 
podman save -o spring-hellowworld-image1_3-oci.tar --format=oci-archive localhost/spring-hellowworld-image:v1.3

### minikube匯入
minikube image load .\spring-hellowworld-image1_3.tar 
#### 在podman使用tar檔會找不到，建議使用cache(minikube cache add docker.io/library/spring-hellowworld-image:v1.4)驗證可用，但docker打包成tar檔 load進minikube沒問題



minikube image load docker.io/library/spring-hellowworld-image:v1.5
#### (測試成功)是可以查到，所以本機不需要特別匯出tar檔

    Examples:
    minikube image load image
    minikube image load image.tar

    Options:
        --daemon=false:
            Cache image from docker daemon

        --overwrite=true:
            Overwrite image even if same image:tag name exists

        --pull=false:
            Pull the remote image (no caching)

        --remote=false:
            Cache image from remote registry

kubectl apply -f deployment.yaml

#### 修改為multi stage測試
###  ADD Dockerfile_m(multi stage)
#### 打包
minikube image build -t spring-hellowworld-image-ms:v1 -f ./Dockerfile_m .
minikube image build -t spring-hellowworld-image-ms:v1 -f ./Dockerfile .

### 檢視
minikube image ls --format table
```
|----------------------------------------------------|----------------------|---------------|--------|
|                       Image                        |         Tag          |   Image ID    |  Size  |
|----------------------------------------------------|----------------------|---------------|--------|
| docker.io/library/spring-hellowworld-image-ms      | v1.0                 | a02cec73dbf0a | 197MB  |
|----------------------------------------------------|----------------------|---------------|--------|
```
#### 修改版本並布署
kubectl apply -f .\deployment.yaml 
#### 露出Service
kubectl expose deployment spring-boot-app --type=NodePort --port=8080
kubectl expose deployment spring-boot-app --type=NodePort --port=8080 target-port=8080
#### Get URL
minikube service spring-boot-app --url

### 更新
#### rollout restart check OK
kubectl rollout restart deployment/spring-boot-app 
kubectl rollout status deployment/spring-boot-app 
kubectl rollout undo deployment/spring-boot-app 
kubectl rollout history deployment/spring-boot-app

kubectl set image deployments/spring-boot-app spring-hellowworld-image-ms:latest


## 2024/1/5 adot test
docker build -t spring-hellowworld-image:v0.1 -f ./Dockerfile .


aws ecr create-repository --repository-name spring-hellowworld-image --region ap-northeast-1


```
{
    "repository": {
        "repositoryArn": "arn:aws:ecr:ap-northeast-1:384533264466:repository/spring-hellowworld-image",
        "registryId": "384533264466",
        "repositoryName": "spring-hellowworld-image",
        "repositoryUri": "384533264466.dkr.ecr.ap-northeast-1.amazonaws.com/spring-hellowworld-image",
        "createdAt": "2024-01-05T06:19:33+00:00",
        "imageTagMutability": "MUTABLE",
        "imageScanningConfiguration": {
            "scanOnPush": false
        },
        "encryptionConfiguration": {
            "encryptionType": "AES256"
        }
    }
}

```

#### ReTag
docker tag spring-hellowworld-image:v0.1 384533264466.dkr.ecr.ap-northeast-1.amazonaws.com/spring-hellowworld-image:v0.1

#### push ecr
aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin 384533264466.dkr.ecr.ap-northeast-1.amazonaws.com

docker push 384533264466.dkr.ecr.ap-northeast-1.amazonaws.com/spring-hellowworld-image:v0.1




