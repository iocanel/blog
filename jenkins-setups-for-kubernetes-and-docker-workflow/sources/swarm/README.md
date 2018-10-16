Jenkins with Swarm Client
-------------------------

With this approach the Jenkins is not responsible for managing nodes.
Instead the nodes are started externally and they connect to the master.
Each slave has the docker binary installed and is connected to the host.

### Highlights
- The master is mounting the docker socket of the host.
- Master shares /var/jenkins_home/workspace volume with build steps.
- Master uses its own executors to execute workflow steps.

#### Pros
- Fast.
- Scalable.
- Robust (doesn't use cloud plugins which buggy)

#### Cons
- Slaves have to started externally.

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
    - The client image is using /workspace as workspace dir. Need to remember when configuring mounts.
    - Swarm will use the WORKDIR as a workspace dir, so they need to be aligned.


