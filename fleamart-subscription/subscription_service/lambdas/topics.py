import json
import re
import boto3
import os
def lambda_handler(event,context):
    sns = boto3.client(
            "sns",
            aws_access_key_id=os.environ.get('aws_access_key_id'), 
            aws_secret_access_key=os.environ.get('aws_secret_access_key'),
            region_name=os.environ.get('region_name')
    )
    email_of_user = event['queryStringParameters']['email']
    response = sns.list_topics()
    topicsARNs = response["Topics"]
    
    topics_subscribed=[]
    for topicArn in topicsARNs:
        print(topicArn)
        if(get_subscribed(topicArn['TopicArn'],email_of_user,sns)):
            topics_subscribed.append(topic_name(topicArn['TopicArn']))
    print(topics_subscribed)
    topics_user = {}
    topics_user['topics'] = topics_subscribed
    topics_user_json = json.dumps(topics_user)
    responseBody={}
    responseBody['statusCode']=200
    responseBody['headers']={}
    responseBody['headers']['Access-Control-Allow-Origin']="*"
    responseBody['headers']['ContentType']="application/json"
    responseBody['body']=topics_user_json
    return responseBody

def get_subscribed(topicArn, email,sns):
    response = sns.list_subscriptions_by_topic(TopicArn=topicArn)
    subscriptions = response["Subscriptions"]
    print(response["Subscriptions"])
    for sub in subscriptions:
        if sub["Endpoint"] == email and sub['SubscriptionArn']!='PendingConfirmation':
            return True


def topic_name(topicArn):
    # coding=utf8
    # the above tag defines encoding for this document and is for Python 2.x compatibility


    regex = r".*:.*:.*:(.*)"


    matches = re.finditer(regex, topicArn, re.MULTILINE)

    for matchNum, match in enumerate(matches, start=1):

        
        for groupNum in range(0, len(match.groups())):
            groupNum = groupNum + 1

            
            return match.group(groupNum)
    
