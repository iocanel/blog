Jenkins with no Slaves (Just Master)
------------------------------------

With this approach the Jenkins master has a fixed number of executors and has access to the docker binary.
Using the docker binary and without the use of docker-plugin or any other cloud plugin, the master executors directly execute docker workflow steps.

### Highlights
- The master is mounting the docker socket of the host.
- Master shares /var/jenkins_home/workspace volume with build steps.
- Master uses its own executors to execute workflow steps.

#### Pros
- Simplest approach of all.
- Minimal use of plugins (root of all evil).

#### Cons
- Builds may wait in queue (no infinite pool of resources).

### Usage

To build the master and slave images:

    ./build-images.sh

To start the master:

    ./run.sh

To cleanup replication controllers and services:

    ./cleanup.sh


### Issues

- Default docker version is 1.7.1 which is incompatible with the plugin (date format error)


### Notes

- Failure to setup correctly the jenkins workspace volume will cause the build to hung forever.

