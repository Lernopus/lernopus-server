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
import com.lernopus.lernopus.exception.ResourceNotFoundException;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.payload.LaCategoryResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.repository.LaCategoryRepository;
import com.lernopus.lernopus.util.AppConstants;
import com.lernopus.lernopus.util.ModelMapper;

@Service
public class LaCategoryService {

    @Autowired
    private LaCategoryRepository laCategoryRepository;

    public PagedResponse<LaCategoryResponse> getAllCategories(int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laTechId");
        Page<LaLearnTechnology> categories = laCategoryRepository.findAll(pageable);

        if(categories.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), categories.getNumber(),
                    categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
        }

        List<LaCategoryResponse> courseResponses = categories.map(category -> {
            return ModelMapper.mapCategoryToCategoryResponse(category);
        }).getContent();

        return new PagedResponse<>(courseResponses, categories.getNumber(),
                categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
    }
    
    public PagedResponse<LaCategoryResponse> getAllParentCategories(int page, int size, boolean isOfficial, String searchedValue) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laTechId");
        Page<LaLearnTechnology> categories = null;
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	categories = laCategoryRepository.getAllParentCategoryForSearch(isOfficial ? "1" : "2", pageable, searchedValue);
        }
        else
        {        	
        	categories = laCategoryRepository.getAllParentCategory(isOfficial ? "1" : "2", pageable);
        }

        if(categories.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), categories.getNumber(),
                    categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
        }

        List<LaCategoryResponse> courseResponses = categories.map(category -> {
            return ModelMapper.mapCategoryToCategoryResponse(category);
        }).getContent();

        return new PagedResponse<>(courseResponses, categories.getNumber(),
                categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
    }

    public LaCategoryResponse getCategoryById(Long categoryId) {
        LaLearnTechnology category = laCategoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", categoryId));

        return ModelMapper.mapCategoryToCategoryResponse(category);
    }
    
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
    
    public PagedResponse<LaCategoryResponse> getAllChildCategory(Long categoryId, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laTechId");
        Page<LaLearnTechnology> categories = null;
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	categories = laCategoryRepository.getAllChildCategoryForSearch(String.valueOf(categoryId), pageable, searchedValue);
        }
        else
        {
        	categories = laCategoryRepository.getAllChildCategory(String.valueOf(categoryId), pageable);
        }

        if(categories.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), categories.getNumber(),
                    categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
        }

        List<LaCategoryResponse> courseResponses = categories.map(category -> {
            return ModelMapper.mapCategoryToCategoryResponse(category);
        }).getContent();

        return new PagedResponse<>(courseResponses, categories.getNumber(),
                categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
    }
}
