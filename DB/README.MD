### psql
psql -U root -d mydb -W
pw=mypassword
db_name=mydb

create table my_table(a varchar(100));
insert into my_table values('1234567890');
select * from my_table;

### test by podman exec minikube
podman exec -it minikube /bin/sh

### scale to 0
kubectl scale deployment postgres --replicas=0

### create secret for pgadmin
kubectl apply -f pgadmin-secret.yaml
mypassword

### 建立pgadmin
kubectl apply -f pgadmin-deploy.yaml


host=192.168.49.2 port=30432 dbname=mydb user=root password=xxxxxxx sslmode=prefer connect_timeout=10 sslcert=<STORAGE_DIR>/.postgresql/postgresql.crt sslkey=<STORAGE_DIR>/.postgresql/postgresql.key
