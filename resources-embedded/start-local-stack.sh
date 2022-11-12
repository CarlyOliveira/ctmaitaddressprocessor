#!/bin/bash

export HTTP_PROXY=
export HTTPS_PROXY=
CONTAINER_NAME=localstack-addressprocessor SERVICES=dynamodb,sqs,sns AWS_REGION=sa-east-1 ACCESS_KEY=fakeAccessKeyId SECRET_KEY=fakeSecretAccessKey  docker-compose -f local-stack/docker-compose.yml  up