## 容器網路測試

### 執行容器
podman run -d --name nginx-1 nginx
podman exec -it nginx-1 /bin/bash 

eth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.88.0.2  netmask 255.255.0.0  broadcast 10.88.255.255        
        inet6 fe80::68ff:7ff:fead:4fb4  prefixlen 64  scopeid 0x20<link>    
        ether 6a:ff:07:ad:4f:b4  txqueuelen 1000  (Ethernet)
        RX packets 8559  bytes 19425550 (18.5 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 8531  bytes 633672 (618.8 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

#### 安裝工具
apt update
apt install net-tools

#### container ifconfig
ifconfig

route
```
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
default         host.containers 0.0.0.0         UG    100    0        0 eth0
10.88.0.0       0.0.0.0         255.255.0.0     U     0      0        0 eth0
```

### podman machine
#### 安裝brctl
yum install bridge-utils
#### 執行brctl
brctl show
bridge name     bridge id               STP enabled     interfaces
podman0         8000.82248f08d562       no              veth0



### 執行容器2
podman run -d --name nginx-2 nginx
podman exec -it nginx-2 /bin/bash 

eth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.88.0.3  netmask 255.255.0.0  broadcast 10.88.255.255
        inet6 fe80::9cec:92ff:fe1d:1020  prefixlen 64  scopeid 0x20<link>
        ether 9e:ec:92:1d:10:20  txqueuelen 1000  (Ethernet)
        RX packets 7762  bytes 19372598 (18.4 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 6200  bytes 482406 (471.0 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0


### podman machine
podman0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.88.0.1  netmask 255.255.0.0  broadcast 10.88.255.255
        inet6 fe80::e4e4:2ff:fe44:dfe9  prefixlen 64  scopeid 0x20<link>
        ether 82:24:8f:08:d5:62  txqueuelen 1000  (Ethernet)
        RX packets 14908  bytes 921406 (899.8 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 16473  bytes 39076978 (37.2 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

veth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet6 fe80::8024:8fff:fe08:d562  prefixlen 64  scopeid 0x20<link>
        ether 82:24:8f:08:d5:62  txqueuelen 1000  (Ethernet)
        RX packets 8574  bytes 637004 (622.0 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 8619  bytes 19500128 (18.5 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

veth1: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet6 fe80::e860:8bff:fe53:2e8e  prefixlen 64  scopeid 0x20<link>
        ether ea:60:8b:53:2e:8e  txqueuelen 1000  (Ethernet)
        RX packets 6334  bytes 493114 (481.5 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 7900  bytes 19580450 (18.6 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
#brctl show
bridge name     bridge id               STP enabled     interfaces
podman0         8000.82248f08d562       no              veth0
                                                        veth1