package com.example.jobfinder.service;

import com.example.jobfinder.model.candidate.CandidatePrincipal;
import com.example.jobfinder.model.job.JobDto;
import com.example.jobfinder.model.job.JobShortDto;
import com.example.jobfinder.model.response.ResponseCreateDto;
import com.example.jobfinder.model.response.ResponseShortOwnDto;
import jakarta.annotation.Nullable;

import java.util.List;

public interface JobCandidateService {
    List<JobShortDto> getPageByTitleQuery(String titleQuery, int pageNumber);

    JobDto getById(long id, @Nullable CandidatePrincipal candidatePrincipal);

    ResponseShortOwnDto sendResponse(ResponseCreateDto createDto, CandidatePrincipal candidatePrincipal);

}
