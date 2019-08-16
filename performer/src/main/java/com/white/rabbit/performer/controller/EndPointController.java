package com.white.rabbit.performer.controller;

import com.white.rabbit.performer.model.Job;
import com.white.rabbit.performer.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Rest controller, provides /perform endpoint
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/perform")
public class EndPointController {
    private JobService jobService;

    @Autowired
    protected EndPointController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(path = "/job")
    @ResponseStatus(HttpStatus.OK)
    public void startJob(@RequestBody Job job) {
        new Thread(() -> jobService.startJob(job.getUserId())).start();
    }

    @GetMapping(path = "/job")
    @ResponseStatus(HttpStatus.OK)
    public List<Job> getJobByUserId(@RequestParam String userId) {
        return jobService.getJobsByUserId(userId);
    }

    @DeleteMapping(path = "/job")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllJobs() {
        jobService.deleteAllJobs();
    }
}