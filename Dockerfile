

# BUILD
FROM gradle:6-jdk11 AS builder2

RUN mkdir -p /build

# Copy build files & setup folder rights
COPY src build/src
COPY build.gradle build/build.gradle

ENV API_KEY=buiderKey
RUN chmod -R 777 build
# Build app
RUN cd build && gradle build --no-daemon -q

FROM alpine:3.14 as runner2
ARG profile=prod
ENV API_KEY=runnerKey
ENV DB_HOST = pg
ENV DB_NAME=postgres
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=12345
ENV activeProfile=$profile

RUN apk add openjdk11-jre

ARG appFolder=/demo
ARG jarFilename=demo-docker.jar

RUN mkdir -p $appFolder

COPY --from=builder2 /home/gradle/build/build/libs/$jarFilename $appFolder/

WORKDIR /$appFolder
# Launch app
ENTRYPOINT ["java", "-jar",\
                 "$jarFilename",\
                 "--spring.profiles.active=$activeProfile"]
