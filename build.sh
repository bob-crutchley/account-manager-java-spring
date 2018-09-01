#!/usr/bin/env bash
set -e
echo "getting artifact and version"
artifactId_and_version=$(mvn -q -Dexec.executable="echo" -Dexec.args='${project.artifactId}::${project.version}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec 2>/dev/null)
artifactId=$(echo ${artifactId_and_version} | awk -F '::' '{ print $1 }')
version=$(echo ${artifactId_and_version} | awk -F '::' '{ print $2 }')
echo ${artifactId}
echo ${version}
echo "building project"
mvn clean package
echo "building docker image"
docker build -t bobcrutchley/${artifactId}:latest --build-arg artifactId=${artifactId} --build-arg version=${version} .
