#!/bin/bash

echo "export DOCKER_HOST=unix:///var/run/docker.sock" >> /root/.profile
echo "DOCKER_DAEMON_ARGS=\"--dns 8.8.8.8\"" >> /root/.profile
#echo "DOCKER_DAEMON_ARGS=\"--dns 8.8.8.8 --dns 8.8.4.4 --registry-mirror=http://${HOSTNAME}:5000\"" >> /root/.profile
source /root/.profile

#start docker in docker
nohup /usr/local/bin/wrapdocker &

#docker run -p 5000:5000 \
#    -e STANDALONE=false \
#    -e MIRROR_SOURCE=http://${DOCKER_REGISTRY_SERVICE_HOST}:${DOCKER_REGISTRY_SERVICE_PORT}\
#    registry &

#Start SSHD
/usr/sbin/sshd -D
