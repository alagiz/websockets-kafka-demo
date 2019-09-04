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

    @Value("${spring.kafka.producer.queueing-job-notify-topic}")
    private String jobNotifyTopic;

    @Value("${spring.kafka.producer.queueing-all-jobs-fetch-topic}")
    private String allJobsTopic;

    @Autowired
    private KafkaTemplate<String, Job> kafkaTemplate;

    public void send(Job job) {
        LOGGER.info("sending job='{}'", job);
        kafkaTemplate.send(jobNotifyTopic, job);
    }

    public void sendAllJobs(Job job) {
        LOGGER.info("sending job list='{}'", job);
        kafkaTemplate.send(allJobsTopic, job);
    }
}
