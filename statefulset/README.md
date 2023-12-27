## 測試Nginx StatefulSet
### 滿足兩個條件 Headless Service和 persistent
#### Headless Service
它通常用於提供域名解析，使每個 Pod 都可以通過 DNS 直接解析到它們自己的域名
clusterIP: None

#### persistent
透過volumeClaimTemplates 去使用PV提供stable storage



## REF
https://www.bogotobogo.com/DevOps/Docker/Docker_Kubernetes_StatefulSet.php

How to Deploy PostgreSQL Statefulset in Kubernetes With High Availability
https://devopscube.com/deploy-postgresql-statefulset/
