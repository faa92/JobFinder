package com.example.jobfinder.service;

import com.example.jobfinder.exception.BusinessException;
import com.example.jobfinder.model.employer.Employer;
import com.example.jobfinder.model.employer.EmployerPrincipal;
import com.example.jobfinder.model.job.Job;
import com.example.jobfinder.model.job.JobOwnDto;
import com.example.jobfinder.model.job.JobUpdateDto;
import com.example.jobfinder.model.response.ResponseDto;
import com.example.jobfinder.model.response.ResponseShortDto;
import com.example.jobfinder.repisitory.EmployerRepository;
import com.example.jobfinder.repisitory.JobRepository;
import com.example.jobfinder.repisitory.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobEmployerServiceImpl implements JobEmployerService {

    public static final int JOBS_PAGE_SIZE = 10;
    public static final int APPLIES_PAGE_SIZE = 5;

    private final JobRepository jobRepository;
    private final ResponseRepository responseRepository;
    private final EmployerRepository employerRepository;

    @Override
    @Transactional
    public List<JobOwnDto> getOwnPage(EmployerPrincipal employerPrincipal, int pageNumber) {
        return jobRepository.findPageByEmployer(employerPrincipal.getId(), JOBS_PAGE_SIZE, pageNumber)
                .stream()
                .map(JobOwnDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public JobOwnDto getOwnById(long id, EmployerPrincipal employerPrincipal) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Job is not found"));
        validateAccess(job, employerPrincipal);
        return JobOwnDto.from(job);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseShortDto> getAppliesPageByJobs(long jobId, int pageNumber, EmployerPrincipal employerPrincipal) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new BusinessException("Job is not found"));
        validateAccess(job, employerPrincipal);

        return responseRepository.findPageByJobWithCandidate(jobId, APPLIES_PAGE_SIZE, pageNumber)
                .stream()
                .map(ResponseShortDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseDto> getPageByOwnJobs(int pageNumber, EmployerPrincipal employerPrincipal) {
        return responseRepository.findPageByEmployerWithJobAndCandidate(employerPrincipal.getId(), APPLIES_PAGE_SIZE, pageNumber)
                .stream()
                .map(ResponseDto::from)
                .toList();
    }

    @Override
    public JobOwnDto create(JobUpdateDto jobUpdateDto, EmployerPrincipal employerPrincipal) {
        Instant createdAt = Instant.now();
        Employer employer = employerRepository.getReferenceById(employerPrincipal.getId());

        Job job = new Job()
                .setEmployer(employer)
                .setTitle(jobUpdateDto.getTitle())
                .setDescription(jobUpdateDto.getDescription())
                .setCreatedAt(createdAt)
                .setActive(jobUpdateDto.isActive());
        jobRepository.create(job);
        return JobOwnDto.from(job);
    }

    @Override
    public JobOwnDto update(long id, JobUpdateDto jobUpdateDto, EmployerPrincipal employerPrincipal) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Job is not found"));
        validateAccess(job, employerPrincipal);

        job.setTitle(jobUpdateDto.getTitle());
        job.setDescription(jobUpdateDto.getDescription());
        job.setActive(jobUpdateDto.isActive());
        return JobOwnDto.from(job);
    }

    private void validateAccess(Job job, EmployerPrincipal employerPrincipal) {
        long authenticatedId = employerPrincipal.getId();
        long ownerId = job.getEmployer().getId();
        if (authenticatedId != ownerId) {
            throw new BusinessException("Access denied");
        }
    }
}
