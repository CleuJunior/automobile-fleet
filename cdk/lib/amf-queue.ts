import * as cdk from 'aws-cdk-lib';
import {Stack, StackProps} from 'aws-cdk-lib';
import * as sqs from 'aws-cdk-lib/aws-sqs';

export class SqsStack extends Stack {
    constructor(scope: cdk.App, id: string, props?: StackProps) {
        super(scope, id, props);

        const queue = new sqs.Queue(this, 'sqs-rent-queue', {
            queueName: 'sqs-rent-queue',
            visibilityTimeout: cdk.Duration.seconds(30),
            retentionPeriod: cdk.Duration.days(4),
        });

        new cdk.CfnOutput(this, 'QueueUrl', {
            value: queue.queueUrl,
        });
    }
}
