#!/usr/bin/env bash
# Add the configuration below to 'docker run' to forward logs to ELK
#          --log-driver fluentd --log-opt fluentd-address=10.120.16.177:24224 --log-opt tag=dms \

echo --------------------------------
echo Running sample api docs
echo --------------------------------

docker run -p 8080:8080 api-docs
