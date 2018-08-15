FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Fexco Software Group <prizeBondsDevelopmentTeam@fexco.com>

ARG SERVER_PORT
ARG DEBUG_PORT

RUN apk -U add \
  bash \
  curl

VOLUME /tmp

ARG WAR_FILE
COPY target/${WAR_FILE} scd-api.war

COPY src/main/docker/entrypoint.sh .
RUN chmod 755 entrypoint.sh

EXPOSE ${SERVER_PORT}
EXPOSE ${DEBUG_PORT}

HEALTHCHECK --interval=15s --timeout=10s --retries=10 \
      CMD curl -f http://localhost:${SERVER_PORT}/scd-api/health || exit 1

ENTRYPOINT ["./entrypoint.sh"]