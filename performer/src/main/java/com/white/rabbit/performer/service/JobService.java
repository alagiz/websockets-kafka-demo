package com.white.rabbit.performer.service;

import com.white.rabbit.performer.model.Job;
import com.white.rabbit.performer.repository.JobStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class JobService {
    private JobStorage jobStorage;
    private final RestTemplate restTemplate;
    private final String baseUri = System.getenv("GATEWAY_URL") + "/notify_job_status/";
    private HttpHeaders headers = new HttpHeaders();

    @Autowired
    public JobService(RestTemplateBuilder restTemplateBuilder, JobStorage jobStorage) {
        this.restTemplate = restTemplateBuilder.build();
        this.jobStorage = jobStorage;
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void startJob(String userId) {
        Job newJob = new Job();

        newJob.setUserId(userId);
        newJob.setJobId(userId + "_" + UUID.randomUUID().toString());
        newJob.setIsJobDone(false);
        newJob.setJobStep(0);

        jobStorage.storeJob(newJob);

        notifyProgress(newJob);
        IntStream.range(1, 7).forEachOrdered(n -> {
            try {
                newJob.setJobStep(n);

                Thread.sleep(10000);

                if (n == 6) newJob.setIsJobDone(true);

                System.out.println("Step " + n + " is executed");

                notifyProgress(newJob);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Job> getJobsByUserId(String userId) {
        return this.jobStorage.getJobsByUserId(userId);
    }

    public void deleteAllJobs() {
        this.jobStorage.deleteAllJobs();
    }

    private void notifyProgress(Job job) {
        HttpEntity<Job> request = new HttpEntity<>(job, headers);

        this.restTemplate.postForObject(baseUri, request, Job.class);
    }
}
