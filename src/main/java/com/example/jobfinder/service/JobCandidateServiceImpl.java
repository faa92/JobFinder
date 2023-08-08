package com.example.jobfinder.service;

import com.example.jobfinder.exception.BusinessException;
import com.example.jobfinder.model.candidate.Candidate;
import com.example.jobfinder.model.candidate.CandidatePrincipal;
import com.example.jobfinder.model.job.Job;
import com.example.jobfinder.model.job.JobDto;
import com.example.jobfinder.model.job.JobShortDto;
import com.example.jobfinder.model.response.Response;
import com.example.jobfinder.model.response.ResponseCreateDto;
import com.example.jobfinder.model.response.ResponseShortOwnDto;
import com.example.jobfinder.repisitory.CandidateRepository;
import com.example.jobfinder.repisitory.JobRepository;
import com.example.jobfinder.repisitory.ResponseRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCandidateServiceImpl implements JobCandidateService {

    private static final int JOBS_PAGE_SIZE = 10;

    private final JobRepository jobRepository;
    private final ResponseRepository responseRepository;
    private final CandidateRepository candidateRepository;

    @Override
    @Transactional
    public List<JobShortDto> getPageByTitleQuery(String titleQuery, int pageNumber) {
        String dbTitleQuery = "%" + titleQuery + "%";
        return jobRepository.findPageActiveAndContainsWithEmployer(dbTitleQuery, JOBS_PAGE_SIZE, pageNumber)
                .stream()
                .map(JobShortDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public JobDto getById(long id, @Nullable CandidatePrincipal candidatePrincipal) {
        Job job = jobRepository.findByWithEmployer(id)
                .orElseThrow(() -> new BusinessException("Job is not found"));
        Response ownResponse = candidatePrincipal == null
                ? null
                : responseRepository.findByJobAndCandidate(id, candidatePrincipal.getId()).orElseThrow(null);

        return JobDto.from(job, ownResponse);
    }

    @Override
    @Transactional
    public ResponseShortOwnDto sendResponse(ResponseCreateDto createDto, CandidatePrincipal candidatePrincipal) {
        Job job = jobRepository.findById(createDto.getId())
                .orElseThrow(() -> new BusinessException("Job is not found"));
        boolean alreadyApplied = responseRepository.findByJobAndCandidate(createDto.getId(), candidatePrincipal.getId())
                .isPresent();
        if (alreadyApplied) {
            throw new BusinessException("Candidate is already applied");
        }
        Candidate candidate = candidateRepository.getReferenceById(candidatePrincipal.getId());
        Instant createdAt = Instant.now();
        Response response = new Response()
                .setCandidate(candidate)
                .setJob(job)
                .setMessage(createDto.getMessage())
                .setCreatedAt(createdAt);
        responseRepository.create(response);
        return ResponseShortOwnDto.from(response);
    }
}
