package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.Employer;
import org.springframework.stereotype.Repository;

@Repository
public class EmployerJpaRepository extends BaseJpaRepository<Employer, Long> implements EmployerRepository {
    public EmployerJpaRepository() {
        super(Employer.class);
    }
}
