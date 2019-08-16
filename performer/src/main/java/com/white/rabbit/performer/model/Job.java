package com.white.rabbit.performer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Job {
    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;

    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String jobId;

    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer jobStep;

    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isJobDone;
}
