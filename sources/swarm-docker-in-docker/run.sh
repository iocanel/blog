#!/bin/bash

oc create -f kubernetes.json
JENKINS_IP=`oc get services | grep jenkins | awk '{print $4}'`
echo "Jenkins listening at http://$JENKINS_IP"
