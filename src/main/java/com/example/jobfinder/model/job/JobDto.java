package com.example.jobfinder.model.job;

import com.example.jobfinder.model.employer.EmployerDto;
import com.example.jobfinder.model.response.Response;
import com.example.jobfinder.model.response.ResponseShortOwnDto;
import jakarta.annotation.Nullable;
import lombok.Value;

import java.util.Optional;

@Value
public class JobDto {
    long id;
    EmployerDto employerDto;
    String title;
    String description;
    @Nullable
    ResponseShortOwnDto responseShortOwnDto;

    public Optional<ResponseShortOwnDto> getOwnResponse() {
        return Optional.ofNullable(responseShortOwnDto);
    }

    public static JobDto from(Job job, @Nullable Response ownResponse) {
        return new JobDto(
                job.getId(),
                EmployerDto.from(job.getEmployer()),
                job.getTitle(),
                job.getDescription(),
                ownResponse == null ? null : ResponseShortOwnDto.from(ownResponse)
        );
    }
}
