#!/bin/bash

echo -e "*********************************"
echo -e "* Iniciando criacao de recursos *"
echo -e "*********************************"

BASE_PATH=/docker-entrypoint-initaws.d

if [ -e "$BASE_PATH/dynamodb" ]; then
  bash $BASE_PATH/dynamodb/dynamodb.sh
fi

if [ -e "$BASE_PATH/sqs" ]; then
  bash $BASE_PATH/sqs/dlq.sh
fi

if [ -e "$BASE_PATH/sqs" ]; then
  bash $BASE_PATH/sqs/queue.sh
fi

if [ -e "$BASE_PATH/sns" ]; then
  bash $BASE_PATH/sns/sns.sh
fi



echo -e ""
echo -e "**********************************"
echo -e "* Criacao de recursos finalizada *"
echo -e "**********************************"
AWS_REGION=sa-east-1 AWS_ACCESS_KEY_ID=fakeAccessKeyId AWS_SECRET_ACCESS_KEY=fakeSecretAccessKey DYNAMO_ENDPOINT=http://localhost:4566 dynamodb-admin