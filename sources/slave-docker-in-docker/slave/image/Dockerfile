FROM jpetazzo/dind

# Make sure the package repository is up to date.
RUN apt-get update
RUN apt-get -y upgrade

# Install a basic SSH server
RUN apt-get install -y openssh-server
RUN sed -i 's|session    required     pam_loginuid.so|session    optional     pam_loginuid.so|g' /etc/pam.d/sshd && \
    sed -i "s/#UsePrivilegeSeparation.*/UsePrivilegeSeparation no/g" /etc/ssh/sshd_config && \
    sed -i 's/PermitRootLogin without-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN mkdir -p /var/run/sshd

# Install JDK 7 (latest edition)
RUN apt-get install -y openjdk-7-jdk

RUN apt-get remove -y lxc-docker && \
    apt-get install -y lxc-docker-1.6.1

# Add user jenkins to the image
# Set password for the jenkins user (you may want to alter this).
RUN echo "root:jenkins" | chpasswd

#Add startup script
ADD ./bin/start-jenkins-slave.sh /usr/bin/start-jenkins-slave.sh

# Standard SSH port
EXPOSE 22

ADD ./bin/wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

ENV LOG=file

CMD ["start-jenkins-slave.sh"]
