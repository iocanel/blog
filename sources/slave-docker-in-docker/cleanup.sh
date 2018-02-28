#!/bin/bash

oc delete services,replicationControllers,pods -l component=jenkins
