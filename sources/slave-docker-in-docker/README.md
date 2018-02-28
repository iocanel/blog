Jenkins with Slaves (Docker in Docker)
--------------------------------------

With this approach the Jenkins master is using the docker-plugin to create Jenkins slaves.
Each slave is starting its own docker daemon (docker in docker) and uses the internal docker to execute workflow steps

### Highlights
- The master is mounting the docker socket of the host.
- The master starts slaves using the docker-plugin
- Slaves use their own docker.
- Build steps are executed inside the slave's dedicated docker.
- Slaves are handling the "bridge" between master and build steps using volumes.

#### Pros
- Each slave is totally isolated from each other.
- Theoretical benefit of being able to replace docker-plugin, with kubernetes-plugin, jenkernetes and what-not.


#### Cons
- "Inception" style of thinking is complex to understand.
- Each slave will download the internet (maybe need to keep slaves around).
- Docker plugin is buggy.
- The current docker version and docker-plugin requires hacky workarounds using lot of mounting.

### Usage

To build the master and slave images:

    ./build-images.sh

To start the master:

    ./run.sh

To cleanup replication controllers and services:

    ./cleanup.sh


### Issues

- Default docker version is 1.7.1 which is incompatible with the plugin (date format error)
- Downgrading to 1.4.1 suffers from random errors starting the internal docker daemon
	- Daemon not started
	- Failure to gracefully stop (device is busy)


### Notes
- Make sure that the SKIP_TLS_VEIFY environmnet variables isn't forwarded to the dind slave (painful).

