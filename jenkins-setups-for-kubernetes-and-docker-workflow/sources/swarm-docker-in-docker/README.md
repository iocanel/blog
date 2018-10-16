Jenkins with Swarm Client and D.I.N.D
-------------------------------------

With this approach the Jenkins is not responsible for managing nodes.
Instead the nodes are started externally and they connect to the master.
Each slave has its own docker engine/daemon running and executes docker workflow steps in the "in Docker".

### Highlights
- The master is mounting the docker socket of the host.
- Swarm clients run their own docker daemon
- Swarm client shares workspace with steps via mounts in the "in docker" host.

#### Pros
- Fast
- Scalable
- Isolated workflow steps


#### Cons
- Slaves have to started externally
- "Inception" style of thinking is complex to understand


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

- The fact that the client stays around means that we have both DIND and caching. Yay!


