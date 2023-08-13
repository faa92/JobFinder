package com.example.jobfinder.model.response;

import com.example.jobfinder.model.candidate.CandidateDto;
import lombok.Value;

import java.time.Instant;

@Value
public class ResponseShortDto {
    long id;
    CandidateDto candidateDto;
    String message;
    Instant createdAt;

    public static ResponseShortDto from(Response response) {
        return new ResponseShortDto(
                response.getId(),
                CandidateDto.from(response.getCandidate()),
                response.getMessage(),
                response.getCreatedAt()
        );
    }
}
