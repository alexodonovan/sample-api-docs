#!/usr/bin/env bash

if [ "$DEBUG" = true ]; then
  printf "Running the application in debug mode\n"
  JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=$DEBUG_PORT,suspend=y"
fi

# Enables application to take PID 1 and receive SIGTERM sent by Docker stop command.
# See here https://docs.docker.com/engine/reference/builder/#/entrypoint
exec java ${JAVA_OPTS} \
       -Djava.security.egd=file:/dev/./urandom -jar scd-api.war