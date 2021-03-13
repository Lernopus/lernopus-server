package com.lernopus.lernopus.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnCourseComments;
import com.lernopus.lernopus.model.LaLearnCourseRating;
import com.lernopus.lernopus.model.LaLearnUserFollowers;
import com.lernopus.lernopus.payload.LaApiResponse;
import com.lernopus.lernopus.payload.LaCourseCommentsRequest;
import com.lernopus.lernopus.payload.LaCourseRatingRequest;
import com.lernopus.lernopus.payload.LaCourseRequest;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.LaCourseViewRequest;
import com.lernopus.lernopus.payload.LaSearchResponse;
import com.lernopus.lernopus.payload.LaUserFollowersRequest;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.security.CurrentUser;
import com.lernopus.lernopus.security.LaUserPrincipal;
import com.lernopus.lernopus.service.LaCourseService;
import com.lernopus.lernopus.util.AppConstants;

/**
 * Created by amernath v on 2019-09-04.
 */

@RestController
@RequestMapping("/api/courses")
public class LaCourseController {

    @Autowired
    private LaCourseService courseService;

    @GetMapping
    public PagedResponse<LaCourseResponse> getCourses(@CurrentUser LaUserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return courseService.getAllChildCourses(currentUser, page, size, searchedValue);
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT') || hasRole('INSTRUCTOR') || hasRole('MANAGER') || hasRole('SUPER_USER')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody LaCourseRequest courseRequest) {
    	LaLearnCourse course = courseService.createCourse(courseRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{courseId}")
                .buildAndExpand(course.getLaCourseId()).toUri();
        if(Objects.nonNull(courseRequest.getLaCourseParentId()))
        {
        	LaCourseResponse laCourseResponse = courseService.getCourseById(courseRequest.getLaCourseParentId());
        	courseService.updateParentAndRootId(course, courseRequest.getLaCourseParentId() , laCourseResponse.getCourseRootId());
        }
        else
        {
        	courseService.updateParentAndRootId(course, course.getLaCourseId() , course.getLaCourseId());
        }
        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "Course Created Successfully"));
    }


    @GetMapping("/{courseId}")
    public LaCourseResponse getCourseById(@CurrentUser LaUserPrincipal currentUser,
                                    @PathVariable Long courseId,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    	LaCourseResponse courseById = courseService.getCourseById(courseId, currentUser);
    	PagedResponse<LaCourseResponse> childCoursePageResponse = courseService.getAllChildCourses(Long.valueOf(courseId), currentUser,page,size);
    	courseById.setChildCoursePageResponse(childCoursePageResponse);
        return courseById;
    }

    @GetMapping("/learnCourseId/{learnCourseId}")
    @PreAuthorize("hasRole('STUDENT') || hasRole('INSTRUCTOR') || hasRole('MANAGER') || hasRole('SUPER_USER')")
    public LaCourseResponse getLaCourseDetails(@PathVariable(value = "learnCourseId") String laCourseId,
                                                         @CurrentUser LaUserPrincipal currentUser,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    	LaCourseResponse courseById = courseService.getCourseById(Long.valueOf(laCourseId), currentUser);
    	PagedResponse<LaCourseResponse> childCoursePageResponse = courseService.getAllChildCourses(Long.valueOf(laCourseId), currentUser,page,size);
    	courseById.setChildCoursePageResponse(childCoursePageResponse);
    	LaCourseViewRequest laCourseViewRequest = new LaCourseViewRequest(laCourseId, String.valueOf(currentUser.getLaUserId()));
    	courseService.uploadCourseViewCount(laCourseViewRequest);
        return courseById;
    }
    
    @PostMapping("/uploadComments")
    public LaLearnCourseComments uploadComments(@Valid @RequestBody LaCourseCommentsRequest laCourseCommentsRequest) {
        return courseService.addComments(laCourseCommentsRequest);
    }
    
    @PostMapping("/uploadRating")
    public LaLearnCourseRating uploadRating(@Valid @RequestBody LaCourseRatingRequest laCourseRatingRequest) {
        return courseService.addRating(laCourseRatingRequest);
    }
    
    @PostMapping("/uploadCourseViewCount")
    public LaLearnCourseRating uploadCourseViewCount(@Valid @RequestBody LaCourseViewRequest laCourseViewRequest) {
        return courseService.uploadCourseViewCount(laCourseViewRequest);
    }
    
    @PostMapping("/addFollowersForUser")
    public LaLearnUserFollowers addFollowersForUser(@Valid @RequestBody LaUserFollowersRequest laUserFollowersRequest) {
        return courseService.addFollowersForUser(laUserFollowersRequest);
    }
    
    @GetMapping("/getAllCoursesForCategory")
    public PagedResponse<LaCourseResponse> getAllCoursesForCategory(@CurrentUser LaUserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "loggedInUserId", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) long loggedInUserId,
                                                @RequestParam(value = "laCategoryId", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) long laCategoryId) {
        return courseService.getAllCoursesForCategory(laCategoryId, currentUser, page, size, searchedValue);
    }
    
    @GetMapping("/getSearchResults")
    public PagedResponse<LaSearchResponse> getSearchResults(
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return courseService.getSearchResults(searchedValue, size);
    }
    
    @GetMapping("/getSearchResultsForParentMapping")
    public PagedResponse<LaSearchResponse> getSearchResultsForParentMapping(
                                                @RequestParam(value = "searchedValue", defaultValue = "") String searchedValue,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return courseService.getSearchResultsForParentMapping(searchedValue, size);
    }


}
