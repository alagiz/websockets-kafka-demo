package com.white.rabbit.performer.consumer;

import com.white.rabbit.performer.model.Job;
import com.white.rabbit.performer.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JobReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobReceiver.class);

    private JobService jobService;

    @Autowired
    protected JobReceiver(JobService jobService) {
        this.jobService = jobService;
    }

    @KafkaListener(topics = "queueing.job.request")
    public void receive(Job job) {
        LOGGER.info("received job='{}'", job.toString());

        new Thread(() -> jobService.startJob(job.getUserId())).start();
    }

    @KafkaListener(topics = "queueing.job.all")
    public void receiveRequestAll(Job job) {
        LOGGER.info("received request for all jobs='{}'", job.getUserId());

        jobService.notifyAllJobs(job);
    }
}