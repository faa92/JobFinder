package com.example.jobfinder.service;

import com.example.jobfinder.model.employer.EmployerPrincipal;
import com.example.jobfinder.model.job.JobOwnDto;
import com.example.jobfinder.model.job.JobUpdateDto;
import com.example.jobfinder.model.response.ResponseDto;
import com.example.jobfinder.model.response.ResponseShortDto;

import java.util.List;

public interface JobEmployerService {
    List<JobOwnDto> getOwnPage(EmployerPrincipal employerPrincipal, int pageNumber);

    JobOwnDto getOwnById(long id, EmployerPrincipal employerPrincipal);

    List<ResponseShortDto> getAppliesPageByJobs(long jobId, int pageNumber, EmployerPrincipal employerPrincipal);

    List<ResponseDto> getPageByOwnJobs(int pageNumber, EmployerPrincipal employerPrincipal);

    JobOwnDto create(JobUpdateDto jobUpdateDto, EmployerPrincipal employerPrincipal);

    JobOwnDto update(long id, JobUpdateDto jobUpdateDto, EmployerPrincipal employerPrincipal);


}
