#!/bin/bash

docker build -t iocanel/jenkins-dind master/image/
docker build -t iocanel/jenkins-slave-dind slave/image/
