package com.example.jobfinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URI;

@Entity
@Table(name = "employer")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Employer extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "website", nullable = false)
    private URI website;
}
