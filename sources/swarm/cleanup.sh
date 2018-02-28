#!/bin/bash

oc delete services,replicationControllers,pods -l component=jenkins
oc delete services,replicationControllers,pods -l component=jenkins-client
