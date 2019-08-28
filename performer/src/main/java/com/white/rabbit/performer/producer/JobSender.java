package com.white.rabbit.performer.producer;

import com.white.rabbit.performer.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobSender.class);

    @Value("${spring.kafka.producer.topic}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, Job> kafkaTemplate;

    public void send(Job job) {
        LOGGER.info("sending job='{}'", job);
        kafkaTemplate.send(jsonTopic, job);
    }
}
