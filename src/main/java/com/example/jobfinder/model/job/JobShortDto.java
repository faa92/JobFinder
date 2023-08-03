package com.example.jobfinder.model.job;

import com.example.jobfinder.model.employer.EmployerDto;
import lombok.Value;

@Value
public class JobShortDto {
    long id;
    EmployerDto employerDto;
    String title;

    public static JobShortDto from(Job job) {
        return new JobShortDto(
                job.getId(),
                EmployerDto.from(job.getEmployer()),
                job.getTitle()
        );
    }
}
