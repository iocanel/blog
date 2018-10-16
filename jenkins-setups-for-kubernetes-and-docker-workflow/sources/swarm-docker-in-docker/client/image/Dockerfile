FROM ubuntu:14.04

# Make sure the package repository is up to date.
RUN apt-get update
RUN apt-get -y upgrade

# Install JDK 7 (latest edition)
RUN apt-get install -y openjdk-7-jdk

# Install Docker prerequisites
RUN apt-get update -qq && apt-get install -qqy \
    apt-transport-https \
    apparmor \
    ca-certificates \
	supervisor \
	curl

# Install Docker from Docker Inc. repositories.
RUN curl -sSL https://get.docker.com/ubuntu/ | sh && \
    apt-get install -y lxc-docker

#Install the wrapper
ADD ./bin/checkloop /usr/local/bin/checkloop
RUN chmod +x /usr/local/bin/checkloop

ADD ./bin/wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker


RUN curl -O -sSL -o swarm-client.jar http://maven.jenkins-ci.org/content/repositories/releases/org/jenkins-ci/plugins/swarm-client/2.0/swarm-client-2.0-jar-with-dependencies.jar

# Create log folder for supervisor, jenkins and docker
RUN mkdir -p /var/log/supervisor
RUN mkdir -p /var/log/docker
RUN mkdir -p /var/log/jenkins

# Copy the supervisor.conf file into Docker
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Start supervisord when running the container
CMD /usr/bin/supervisord -c /etc/supervisor/conf.d/supervisord.conf
