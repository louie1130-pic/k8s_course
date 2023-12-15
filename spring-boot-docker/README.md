## 建立image

### 打包
mvn clean install

### 本機unit test
java -jar .\target\spring-boot-docker-0.0.1-SNAPSHOT.jar


### 透過Dockerfile建立image
minikube image build -t spring-hellowworld-image:v1.1 -f ./Dockerfile .


## 建立Service for test

### 使用NodePort去建立service並連接container port
kubectl expose deployment spring-boot-app --type=NodePort --port=8080

### 查詢本機(host) url和port
minikube service spring-boot-app --url