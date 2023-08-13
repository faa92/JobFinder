package com.example.jobfinder.model.candidate;

import lombok.Value;

@Value
public class CandidateDto {
    String email;
    String firstName;
    String lastName;
    String resume;

    public static CandidateDto from(Candidate candidate) {
        return new CandidateDto(
                candidate.getEmail(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getResume()
        );
    }
}
