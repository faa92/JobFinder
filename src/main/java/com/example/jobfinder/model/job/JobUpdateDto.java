package com.example.jobfinder.model.job;

import lombok.Value;

@Value
public class JobUpdateDto {
    String title;
    String description;
    boolean active;
}
