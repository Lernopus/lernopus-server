package com.lernopus.lernopus.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lernopus.lernopus.exception.BadRequestException;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaUserSummary;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.repository.LaCategoryRepository;
import com.lernopus.lernopus.repository.LaUserRepository;
import com.lernopus.lernopus.util.AppConstants;
import com.lernopus.lernopus.util.ModelMapper;

@Service
public class LaUserService {

    @Autowired
    private LaUserRepository laUserRepository;
    
    @Autowired
    private LaCategoryRepository laCategoryRepository;
    
    public PagedResponse<LaUserSummary> getAllAuthors(long loggedInUserId, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laUserFullName");
        Page<LaLearnUser> authors = null;
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	authors = laUserRepository.getAllAuthorForSearch(loggedInUserId, pageable, searchedValue);
        }
        else
        {        	
        	authors = laUserRepository.getAllAuthor(loggedInUserId, pageable);
        }

        if(authors.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), authors.getNumber(),
                    authors.getSize(), authors.getTotalElements(), authors.getTotalPages(), authors.isLast());
        }

        List<LaUserSummary> authorResponses = authors.map(author -> {
            return ModelMapper.mapUserToUserResponse(author);
        }).getContent();

        return new PagedResponse<>(authorResponses, authors.getNumber(),
                authors.getSize(), authors.getTotalElements(), authors.getTotalPages(), authors.isLast());
    }
    
    public PagedResponse<LaUserSummary> getAllAuthorsForCategory(long categoryId, long loggedInUserId, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);

        LaLearnTechnology laLearnTechnology = laCategoryRepository.getOne(categoryId);
        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laUserFullName");
        Page<LaLearnUser> authors = null;
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	authors = laUserRepository.getAllAuthorForCategoryForSearch(laLearnTechnology, loggedInUserId, pageable, searchedValue);
        }
        else
        {        	
        	authors = laUserRepository.getAllAuthorForCategory(laLearnTechnology, loggedInUserId, pageable);
        }

        if(authors.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), authors.getNumber(),
                    authors.getSize(), authors.getTotalElements(), authors.getTotalPages(), authors.isLast());
        }

        List<LaUserSummary> authorResponses = authors.map(author -> {
            return ModelMapper.mapUserToUserResponse(author);
        }).getContent();

        return new PagedResponse<>(authorResponses, authors.getNumber(),
                authors.getSize(), authors.getTotalElements(), authors.getTotalPages(), authors.isLast());
    }
    
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
