#!/usr/bin/env bash
mvn clean install && docker build -t api-docs . && ./run.sh