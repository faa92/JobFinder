package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.job.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JobJpaRepository extends BaseJpaRepository<Job, Long> implements JobRepository {
    public JobJpaRepository() {
        super(Job.class);
    }

    @Override
    public List<Job> findPageByEmployer(long employerId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT job
                        FROM Job job
                        WHERE job.employer.id = :employerId
                        ORDER BY job.createdAt DESC 
                        """, Job.class)
                .setParameter("employerId", employerId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<Job> findPageActiveAndContainsWithEmployer(String titleQuery, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT job
                        FROM Job job
                        JOIN FETCH job.employer
                        WHERE job.active
                        AND job.title ILIKE :titleQuery
                        ORDER BY job.createdAt DESC 
                        """, Job.class)
                .setParameter("titleQuery", titleQuery)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public Optional<Job> findByWithEmployer(long jobId) {
        return entityManager.createQuery("""
                        SELECT job
                        FROM Job job
                        JOIN FETCH job.employer
                        WHERE job.id = :jobId
                        """, Job.class)
                .setParameter("jobId", jobId)
                .getResultStream()
                .findFirst();
    }
}
