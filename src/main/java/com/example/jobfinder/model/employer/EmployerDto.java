package com.example.jobfinder.model.employer;

import lombok.Value;

import java.net.URI;

@Value
public class EmployerDto {
    long id;
    String name;
    URI website;

    public static EmployerDto from(Employer employer) {
        return new EmployerDto(
                employer.getId(),
                employer.getName(),
                employer.getWebsite()
        );
    }
}
