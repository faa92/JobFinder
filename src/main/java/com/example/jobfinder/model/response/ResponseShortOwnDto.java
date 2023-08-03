package com.example.jobfinder.model.response;

import lombok.Value;

import java.time.Instant;

@Value
public class ResponseShortOwnDto {
    long id;
    String message;
    Instant createdAt;

    public static ResponseShortOwnDto from(Response response) {
        return new ResponseShortOwnDto(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt()
        );
    }
}
