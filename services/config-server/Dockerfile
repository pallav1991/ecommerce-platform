FROM alpine:latest

# Install OpenJDK, git, curl, and other utilities
RUN apk add --no-cache openjdk17 git curl ca-certificates openssl \
    && update-ca-certificates

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"


ADD target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","config-server.jar"]