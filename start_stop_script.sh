#!/bin/bash

# Stop minikube
minikube stop

# Wait for 10 seconds
sleep 10

# Stop podman machine
podman machine stop

# Wait for 10 seconds
sleep 10

# Start podman machine
podman machine start

# Wait for 10 seconds
sleep 10

# Start minikube
minikube start
