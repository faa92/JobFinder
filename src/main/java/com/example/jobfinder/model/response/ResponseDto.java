package com.example.jobfinder.model.response;

import com.example.jobfinder.model.candidate.CandidateDto;
import com.example.jobfinder.model.job.JobOwnDto;
import lombok.Value;

import java.time.Instant;

@Value
public class ResponseDto {
    long id;
    JobOwnDto jobOwnDto;
    CandidateDto candidateDto;
    String message;
    Instant createdAt;

    public static ResponseDto from(Response response) {
        return new ResponseDto(
                response.getId(),
                JobOwnDto.from(response.getJob()),
                CandidateDto.from(response.getCandidate()),
                response.getMessage(),
                response.getCreatedAt()
        );
    }
}
