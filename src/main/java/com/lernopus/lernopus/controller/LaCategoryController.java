package com.lernopus.lernopus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lernopus.lernopus.payload.LaCategoryResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.security.CurrentUser;
import com.lernopus.lernopus.security.LaUserPrincipal;
import com.lernopus.lernopus.service.LaCategoryService;
import com.lernopus.lernopus.util.AppConstants;

/**
 * Created by amernath v on 2019-09-04.
 */

@RestController
@RequestMapping("/api/categories")
public class LaCategoryController {

    @Autowired
    private LaCategoryService categoryService;

    @GetMapping
    public PagedResponse<LaCategoryResponse> getOfficialCategories(@CurrentUser LaUserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "isOfficial", defaultValue = "false") boolean isOfficial) {
        return categoryService.getAllParentCategories(page, size, isOfficial, searchedValue);
    }
    
    @GetMapping("/getSpecialCategories")
    public PagedResponse<LaCategoryResponse> getSpecialCategories(@CurrentUser LaUserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "isOfficial", defaultValue = "false") boolean isOfficial) {
        return categoryService.getAllParentCategories(page, size, isOfficial, searchedValue);
    }
    
    @GetMapping("/getSubCategories")
    @PreAuthorize("hasRole('USER')")
    public PagedResponse<LaCategoryResponse> getSubCategories(@CurrentUser LaUserPrincipal currentUser,
    											@RequestParam(value = "categoryId") Long categoryId,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return categoryService.getAllChildCategory(Long.valueOf(categoryId),page,size, searchedValue);
    }


    @GetMapping("/learnCategoryId/{categoryId}")
    @PreAuthorize("hasRole('USER')")
    public LaCategoryResponse getCourseById(@CurrentUser LaUserPrincipal currentUser,
    											@PathVariable(value = "categoryId") Long categoryId, 
    											@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    											@RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
    											@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    	LaCategoryResponse courseById = categoryService.getCategoryById(categoryId);
    	PagedResponse<LaCategoryResponse> childCoursePageResponse = categoryService.getAllChildCategory(Long.valueOf(categoryId),page,size, searchedValue);
    	courseById.setChildCoursePageResponse(childCoursePageResponse);
        return courseById;
    }

}
