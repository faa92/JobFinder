package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.response.Response;

import java.util.List;

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
}
