@echo off

echo ### Criando Queue...
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name sqs-rent-queue

echo ### Criando SNS...
aws --endpoint http://localhost:4566 --profile localstack sns create-topic --name sns-comment-topic
aws --endpoint http://localhost:4566 --profile localstack sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:sns-comment-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:sqs-rent-queue --attributes '{"RawMessageDelivery":"true"}'
