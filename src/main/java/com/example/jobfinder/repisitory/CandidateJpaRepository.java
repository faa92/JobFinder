package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.Candidate;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateJpaRepository extends BaseJpaRepository<Candidate, Long> implements CandidateRepository {
    public CandidateJpaRepository() {
        super(Candidate.class);
    }
}
