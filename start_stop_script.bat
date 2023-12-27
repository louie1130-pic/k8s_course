@echo off

REM Stop minikube
minikube stop

REM Wait for 10 seconds
ping -n 10 127.0.0.1 > nul

REM Stop podman machine
podman machine stop

REM Wait for 10 seconds
ping -n 10 127.0.0.1 > nul

REM Start podman machine
podman machine start

REM Wait for 10 seconds
ping -n 10 127.0.0.1 > nul

REM Start minikube
minikube start
