#!/bin/bash


export HTTP_PROXY=""
export HTTPS_PROXY=""

echo -e "Publicando mensagem do arquivo Autorizacao"
aws sqs send-message --cli-input-json file://messages/pix_autorizado_payload_aws_publish.json --endpoint-url=http://localhost:4566 --region sa-east-1

