package com.demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class KafkaProducer {
    private final KafkaTemplate <String, RiderLocation> kafkaTemplate;
    public KafkaProducer(KafkaTemplate<String, RiderLocation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
       // RiderLocation riderLocation = new RiderLocation("rider123", 37.7749, -122.4194);

       // Generate random RiderLocation
        Random random = new Random();
        String riderId = UUID.randomUUID().toString();
        double latitude = -90 + (90 - (-90)) * random.nextDouble(); // Range: -90 to 90
        double longitude = -180 + (180 - (-180)) * random.nextDouble(); // Range: -180 to 180

        RiderLocation riderLocation = new RiderLocation(riderId, latitude, longitude);

        // Convert the RiderLocation object to a string representation
        kafkaTemplate.send("topic_from_bin_1", riderLocation);
        return "Message sent: " + riderLocation.getRiderId();
    }

}
