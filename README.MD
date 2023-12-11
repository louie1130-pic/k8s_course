### psql
psql -U root -d mydb -W
mypassword

create table my_table(a varchar(100));
insert into my_table values('1234567890');
select * from my_table;

### test by podman exec minikube
podman exec -it minikube /bin/sh

### scale to 0
kubectl scale deployment postgres --replicas=0

### pgadmin
kubectl apply -f pgadmin-deploy.yaml