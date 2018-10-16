FROM jenkins
MAINTAINER iocanel@gmail.com

ENV DOCKER_HOST tcp://localhost:2375
ENV KUBERNETES_MASTER https://${KUBERNETES_SERVICE_HOST}:${KUBERNETES_SERVICE_PORT}
ENV KUBERNETES_TRUST_CERT true
ENV KUBERNETES_TRUST_CERTIFICATES true
ENV SKIP_TLS_VERIFY true
ENV KUBERNETES_NAMESPACE default
ENV JENKINS_UC https://updates.jenkins-ci.org

# Install package dependencies as root
USER root

RUN apt-get update -y && apt-get install -y socat bzr

COPY plugins.sh /usr/local/bin/
COPY plugins.txt /usr/share/jenkins/ref/
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/ref/plugins.txt

# lets configure and add default jobs
COPY jenkins/*.xml $JENKINS_HOME/
COPY jenkins/jobs $JENKINS_HOME/jobs

COPY bin/* /usr/local/bin/
RUN chown -R jenkins:jenkins $JENKINS_HOME/ /usr/local/bin/jenkins*.sh

COPY setup-credentials.groovy /usr/share/jenkins/ref/init.groovy.d/setup-credentials.groovy
ENTRYPOINT ["/usr/local/bin/jenkins.sh"]
