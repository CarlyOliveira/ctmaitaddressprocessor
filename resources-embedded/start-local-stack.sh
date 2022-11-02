#!/bin/bash

export HTTP_PROXY=
export HTTPS_PROXY=
CONTAINER_NAME=localstack-addressprocessor SERVICES=dynamodb,sqs,sns  docker-compose -f local-stack/docker-compose.yml  up