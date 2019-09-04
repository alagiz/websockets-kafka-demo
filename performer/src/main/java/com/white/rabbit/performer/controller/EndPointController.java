package com.white.rabbit.performer.controller;

import com.white.rabbit.performer.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(path = "/job")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllJobs() {
        jobService.deleteAllJobs();
    }
}