aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name sqs-rent-queue
aws --endpoint http://localhost:4566 --profile localstack sns create-topic --name sns-comment-topic

aws --endpoint http://localhost:4566 --profile localstack sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:sns-comment-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:sqs-rent-queue --attributes '{"RawMessageDelivery":"true"}'

aws --endpoint http://localhost:4566 --profile localstack lambda create-function --function-name Comment-Service-Worker --runtime java17 --role arn:aws:iam::000000000000:role/locallambda --handler br.com.comment.work.Function::handleRequest --zip-file fileb://../comment-worker/target/comment-worker-1.0.0.jar

aws --endpoint http://localhost:4566 --profile localstack lambda add-permission --function-name Comment-Service-Worker --statement-id sqs-trigger --action "lambda:InvokeFunction" --principal sqs.amazonaws.com --source-arn arn:aws:sqs:us-east-1:000000000000:sqs-rent-queue

aws --endpoint http://localhost:4566 --profile localstack lambda create-event-source-mapping --function-name Comment-Service-Worker --batch-size 1 --event-source-arn arn:aws:sqs:us-east-1:000000000000:sqs-rent-queue

aws --endpoint http://localhost:4566 --profile localstack logs create-log-group --log-group-name /aws/lambda/Comment-Service-Worker
aws --endpoint http://localhost:4566 --profile localstack logs create-log-stream --log-group-name /aws/lambda/Comment-Service-Worker --log-stream-name lambda-log-stream

aws --endpoint http://localhost:4566 --profile localstack iam create-policy --policy-name LambdaCloudWatchPolicy --policy-document file://lambda-cloudwatch-policy.json

aws --endpoint http://localhost:4566 --profile localstack iam create-role --role-name LambdaExecutionRole --assume-role-policy-document file://lambda-cloudwatch-role.json
aws --endpoint http://localhost:4566 --profile localstack iam attach-role-policy --role-name LambdaExecutionRole --policy-arn arn:aws:iam::000000000000:policy/LambdaCloudWatchPolicy
aws --endpoint http://localhost:4566 --profile localstack lambda update-function-configuration --function-name Comment-Service-Worker --role arn:aws:iam::000000000000:role/LambdaExecutionRole
