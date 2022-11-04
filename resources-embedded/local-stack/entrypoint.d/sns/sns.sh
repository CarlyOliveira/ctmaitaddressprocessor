#!/bin/bash

create_topics() {
  for TOPIC_FILE in "${TOPIC_FILES[@]}"; do
    echo -e "Creating topic from file [$TOPIC_FILE]"
    aws sns create-topic --cli-input-json file://"$TOPIC_FILE" --endpoint-url=http://localhost:4566 >> /dev/null
  done

  echo -e "Created topics: "
  aws sns list-topics
}

sns_subscribe() {
  echo -e "Subscribe queue"
  aws sns subscribe --topic-arn arn:aws:sns:sa-east-1:000000000000:ctmait-address-events --protocol sqs --notification-endpoint arn:aws:sqs:sa-east-1:000000000000:ctmait-address-inclusao --attributes '{"FilterPolicy": "{\"event\": [\"inclusao\"]}"}' --endpoint-url=http://localhost:4566 --region=sa-east-1
  aws sns subscribe --topic-arn arn:aws:sns:sa-east-1:000000000000:ctmait-address-events --protocol sqs --notification-endpoint arn:aws:sqs:sa-east-1:000000000000:ctmait-address-alteracao --attributes '{"FilterPolicy": "{\"event\": [\"alteracao\"]}"}' --endpoint-url=http://localhost:4566 --region=sa-east-1

}

publish_events() {
  for EVENT_FILE in "${EVENT_FILES[@]}"; do
    echo -e "Sending event from file [$EVENT_FILE]"
    aws sns publish --cli-input-json file://"$EVENT_FILE" >> /dev/null
  done
}

echo -e "+------------------------+"
echo -e "| Creating SNS workloads |"
echo -e "+------------------------+"

BASE_PATH=/docker-entrypoint-initaws.d
TOPIC_FILES=("$BASE_PATH"/sns/topics/*.json)
EVENT_FILES=("$BASE_PATH"/sns/events/*.json)

if [ -e "${TOPIC_FILES[0]}" ]; then
  echo -e "Creating SNS topics..."
  create_topics
else
  echo -e "SNS topic scripts not found"
fi

echo -e ""

sns_subscribe

echo -e ""


if [ -e "${EVENT_FILES[0]}" ]; then
  echo -e "Sending events into SNS topics..."
  publish_events
else
  echo -e "SNS event scripts not found"
fi

echo -e "+---------------------------------+"
echo -e "| Finished SNS workloads creation |"
echo -e "+---------------------------------+"
echo -e ""
