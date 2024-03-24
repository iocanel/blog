#!/bin/bash

docker build -t iocanel/jenkins master/image/
docker build -t iocanel/jenkins-slave slave/image/
