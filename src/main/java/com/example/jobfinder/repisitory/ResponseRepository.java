package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.response.Response;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends BaseRepository<Response, Long> {

    List<Response> findPageByJobWithCandidate(long jobId, int pageSize, int pageNumber);

    List<Response> findPageByEmployerWithJobAndCandidate(long employerId, int pageSize, int pageNumber);

    Optional<Response> findByJobAndCandidate(long jobId, long candidateId);

}
