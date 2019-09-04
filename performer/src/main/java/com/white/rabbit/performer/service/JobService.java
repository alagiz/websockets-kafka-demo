package com.white.rabbit.performer.service;

import com.white.rabbit.performer.model.Job;
import com.white.rabbit.performer.producer.JobSender;
import com.white.rabbit.performer.repository.JobStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class JobService {
    private JobStorage jobStorage;
    private JobSender jobSender;

    @Autowired
    public JobService(JobStorage jobStorage, JobSender jobSender) {
        this.jobStorage = jobStorage;
        this.jobSender = jobSender;
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
        this.jobSender.send(job);
    }

    public void notifyAllJobs(Job job) {
        List<Job> jobList = this.getJobsByUserId(job.getUserId());
        jobList = jobList == null ? new ArrayList<>() : jobList;
        Job jobListContainer = new Job();

        jobListContainer.setJobList(jobList);
        jobListContainer.setUserId(job.getUserId());

        this.jobSender.sendAllJobs(jobListContainer);
    }
}
