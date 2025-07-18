package com.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "my-topic", groupId = "my-group-new1")
    public void listen1(String message) {
        System.out.println("Received message from consumer 1: " + message);
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group-new2")
    public void listen2(String message) {
        System.out.println("Received message from consumer 2: " + message);
    }

    @KafkaListener(topics = "topic_from_bin_1", groupId = "my-group-new3-bin1")
    public void listenRiderLocation(RiderLocation location) {
        System.out.println("Received Location : " + location.getRiderId() +
                           " at latitude: " + location.getLatitude() +
                           ", longitude: " + location.getLongitude());
    }
}
