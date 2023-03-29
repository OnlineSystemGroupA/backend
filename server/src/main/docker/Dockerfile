FROM openjdk:17
RUN mkdir -p /usr/local/licensingservice
ADD  @project.build.finalName@.jar /usr/local/stcos/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh