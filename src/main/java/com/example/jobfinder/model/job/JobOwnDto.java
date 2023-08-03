package com.example.jobfinder.model.job;

import lombok.Value;

import java.time.Instant;

@Value
public class JobOwnDto {
    long id;
    String title;
    String description;
    boolean active;
    Instant createdAt;

    public static JobOwnDto from(Job job) {
        return new JobOwnDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.isActive(),
                job.getCreatedAt()
        );
    }
}
