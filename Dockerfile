FROM jschuwan/jschuwan:bc485abe7f7b4f47a11eba36c84169a8
USER root
RUN apt-get update && apt-get upgrade -y
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean:1.25.3 docker-workflow:1.28"
