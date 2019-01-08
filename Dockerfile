FROM openjdk:8-jdk

RUN apt-get update \
    && apt-get install -y openssh-server vim

ADD ./hadoop-2.9.1.tar.gz soft/

WORKDIR /soft/hadoop-2.9.1
RUN sed -i 's/${JAVA_HOME}/\/docker-java-home/' etc/hadoop/hadoop-env.sh \
    && ssh-keygen -t rsa -N "" -f ~/.ssh/id_rsa \
    && cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

ADD ./entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]