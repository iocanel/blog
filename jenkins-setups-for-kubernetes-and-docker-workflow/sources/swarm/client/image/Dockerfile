FROM centos:7

RUN yum install -y docker-1.6.0

# Install JDK 7 (latest edition)
RUN yum install -y java-1.7.0-openjdk

RUN curl -O -sSL -o swarm-client.jar http://maven.jenkins-ci.org/content/repositories/releases/org/jenkins-ci/plugins/swarm-client/2.0/swarm-client-2.0-jar-with-dependencies.jar

ENV JENKINS_HOME /var/jenkins_home
WORKDIR /var/jenkins_home

ENTRYPOINT ["java", "-jar", "/swarm-client-2.0-jar-with-dependencies.jar", "-disableSslVerification", "-master", "http://jenkins:80"]
