Single Docker Daemon
--------------------

With this approach the Jenkins master is using the docker-plugin to spin slave containers. Slaves execute the docker workflow.

### Highlights

Master mounts /var/run/docker.sock and exposes via http using socat.
Master connects to slave via SSHD using the root:jenkins account.
The slave reuses the docker socket and binary of the host.

#### Pros
- Maybe the simplest approach.


#### Cons
- Docker plugin is buggy.
- May not work that great in a multi-node environment (no guarantee master and slave will be able to share data).

### Usage

To build the master and slave images:

    ./build-images.sh

To start the master:

    ./run.sh

To cleanup replication controllers and services:

    ./cleanup.sh


### Notes

- It is essential to configure the master to start slave containers using -v /home/jenkins/:/home/jenkins/ or the build will hung
