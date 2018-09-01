FROM java:8
ARG artifactId
ARG version
ENV artifactId=${artifactId}
ENV version=${version}
COPY target/${artifactId}-${version}.jar ${artifactId}-${version}.jar
ENTRYPOINT java -jar ${artifactId}-${version}.jar -Drun.arguments=--spring.datasource.host="jdbc:mysql://mysql:3306/accountmanager?autoReconnect=true&useSSL=false"
