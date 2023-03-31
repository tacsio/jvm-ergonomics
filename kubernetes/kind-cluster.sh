#!/bin/bash


docker build -t jvm-ergonomic .

kind create cluster --config test-cluster.yaml

kind load docker-image jvm-ergonomic:latest

kubectl apply -f test-deploy.yaml

