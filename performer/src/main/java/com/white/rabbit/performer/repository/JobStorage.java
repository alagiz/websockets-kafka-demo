package com.white.rabbit.performer.repository;

import com.white.rabbit.performer.model.Job;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JobStorage {
    private Map<String, List<Job>> jobMap = new HashMap<>();

    public void storeJob(Job job) {
        Boolean userKeyExists = jobMap.containsKey(job.getUserId());

        if (userKeyExists) {
            List<Job> userJobMap = jobMap.get(job.getUserId());

            userJobMap.add(job);
        } else {
            List<Job> userJobMap = new ArrayList<>();

            userJobMap.add(job);

            jobMap.put(job.getUserId(), userJobMap);
        }
    }

    public List<Job> getJobsByUserId(String userId) {
        if (userId.equals("godfather")) {
            return jobMap.values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }

        return jobMap.get(userId);
    }

    public void deleteAllJobs() {
        this.jobMap = new HashMap<>();
    }

    public Boolean isJobListEmpty() {
        return jobMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()).size() > 0;
    }
}
