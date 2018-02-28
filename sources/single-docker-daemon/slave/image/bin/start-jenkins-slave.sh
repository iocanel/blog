#!/bin/bash

#copy ssh keys
mkdir -p /root/.ssh
chmod 700 /root/.ssh
cp /home/jenkins/ssh-keys/authorized_keys /root/.ssh/authorized_keys
chown -R root:root /root/.ssh
chmod 600 /root/.ssh/authorized_keys

#Start SSHD
/usr/sbin/sshd -D
