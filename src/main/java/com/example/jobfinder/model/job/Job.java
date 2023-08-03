package com.example.jobfinder.model.job;

import com.example.jobfinder.model.BaseEntity;
import com.example.jobfinder.model.employer.Employer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Entity
@Table(name = "job")
@Getter
@Setter
@Accessors(chain = true)
public class Job extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "active", nullable = false)
    private boolean active;

}
