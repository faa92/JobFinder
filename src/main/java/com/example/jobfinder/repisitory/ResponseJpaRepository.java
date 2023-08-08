package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.response.Response;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ResponseJpaRepository extends BaseJpaRepository<Response, Long> implements ResponseRepository {

    public ResponseJpaRepository() {
        super(Response.class);
    }

    @Override
    public List<Response> findPageByJobWithCandidate(long jobId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                         SELECT response 
                         FROM Response response
                         JOIN FETCH response.candidate
                         WHERE response.job.id = : jobId
                         ORDER BY response.createdAt DESC                    
                        """, Response.class)
                .setParameter("jobId", jobId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<Response> findPageByEmployerWithJobAndCandidate(long employerId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT response
                        FROM Response response
                        JOIN FETCH response.job
                        JOIN FETCH response.candidate
                        WHERE response.job.employer.id = : employerId
                        ORDER BY response.createdAt DESC 
                        """, Response.class)
                .setParameter("employerId", employerId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public Optional<Response> findByJobAndCandidate(long jobId, long candidateId) {
        return entityManager.createQuery("""
                        SELECT response
                        FROM Response response
                        WHERE response.job.id = :jobId
                        AND response.candidate.id = :candidateId
                        """, Response.class)
                .setParameter("jobId", jobId)
                .setParameter("candidateId", candidateId)
                .getResultStream()
                .findFirst();
    }
}
