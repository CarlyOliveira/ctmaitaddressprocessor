#!/bin/bash

create_queues()
{
  for QUEUE_FILE in "${QUEUE_PATH[@]}"; do
    echo -e "Criando fila do arquivo [$QUEUE_FILE]"
    aws  sqs create-queue --cli-input-json file://"$QUEUE_FILE" --endpoint-url=http://localhost:4566 >> /dev/null
  done
}

echo -e ""
echo -e "*************************************"
echo -e "* Iniciando criacao de recursos SQS *"
echo -e "*************************************"

BASE_PATH=/docker-entrypoint-initaws.d
QUEUE_PATH=("$BASE_PATH"/sqs/dlq/*.json)

if [ -e "${QUEUE_PATH[0]}" ]; then
  echo -e ""
  echo -e "Iniciando criacao de filas SQS DLQ..."
  create_queues
else
  echo -e ""
  echo -e "Scripts de criacao de filas SQS DLQ nao encontrados..."
fi

echo -e ""
echo -e "**************************************************"
echo -e "* Processo de criacao de recursos SQS finalizado *"
echo -e "**************************************************"
