package com.example.jobfinder.repisitory;

import com.example.jobfinder.model.job.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends BaseRepository<Job, Long> {

    List<Job> findPageByEmployer(long employerId, int pageSize, int pageNumber);

    List<Job> findPageActiveAndContainsWithEmployer(String title, int limit, int offset);

    Optional<Job> findByWithEmployer(long jobId);
}
