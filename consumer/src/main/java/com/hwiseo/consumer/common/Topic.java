package com.hwiseo.consumer.common;

public enum Topic {

    MEMBER("member"),
    ACCOUNT("account");

    private final String topicName;

    Topic(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return this.topicName;
    }
}
enum Category {
    MEMBER, ACCOUNT
}