package com.example.jobfinder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)   //todo
    @JoinColumn(name = "job_id")
    @Column(nullable = false)
    private Job jobId;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @Column(nullable = false)
    private Candidate candidateId;

    @Column(nullable = false)
    private String message;

    @Column(updatable = false)
    private Instant createdAt;

}
