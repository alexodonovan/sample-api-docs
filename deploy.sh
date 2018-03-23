#!/usr/bin/env bash
docker tag api-docs:latest 602568515010.dkr.ecr.us-east-1.amazonaws.com/prizebonds:latest &&
eval $(aws ecr get-login --region us-east-1 | sed 's|https://||') &&
docker push 602568515010.dkr.ecr.us-east-1.amazonaws.com/prizebonds:latest

