#!/bin/bash

docker build -t iocanel/jenkins-swarm-master master/image/
docker build -t iocanel/jenkins-swarm-client client/image/
