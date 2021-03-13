package com.lernopus.lernopus.controller.api;

import java.net.URI;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.payload.LaApiResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.payload.api.LaApiFilterSorterRequest;
import com.lernopus.lernopus.payload.api.LaLearnCourseApiResponse;
import com.lernopus.lernopus.service.api.LaCourseApiService;
import com.lernopus.lernopus.util.AppConstants;

@RestController
@RequestMapping("/backend/api/LaLearnCourse")
public class LaCourseApiController {
    
    @Autowired
    private LaCourseApiService laCourseApiService;
    
    @GetMapping("/getAllLaLearnCourse")
    public PagedResponse<LaLearnCourseApiResponse> getAllLaLearnCourse(
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return laCourseApiService.getAllWithFilterLaLearnCourse(page, size, null);
    }
    
    @PostMapping("/getAllWithFilterLaLearnCourse")
    public PagedResponse<LaLearnCourseApiResponse> getAllWithFilterLaLearnCourse(
                                                @Valid @RequestBody LaApiFilterSorterRequest laApiFilterSorterRequest) {
    	return laCourseApiService.getAllWithFilterLaLearnCourse(laApiFilterSorterRequest.getPage(), laApiFilterSorterRequest.getSize(), laApiFilterSorterRequest);
    }
    
    @GetMapping("/getLaLearnCourse/{key}")
    public LaLearnCourseApiResponse getLaLearnCourse(@PathVariable(value = "key") String key) {
        return laCourseApiService.getLaLearnCourse(key);
    }
    
    @PostMapping("/insertLaLearnCourse")
    public ResponseEntity<?> insertLaLearnCourse(@Valid @RequestBody LaLearnCourseApiResponse courseRequest) {
    	LaLearnCourse course = laCourseApiService.insertLaLearnCourse(courseRequest);

    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{courseId}")
                .buildAndExpand(course.getLaCourseId()).toUri();
        if(Objects.nonNull(courseRequest.getLaCourseParentId()))
        {
        	LaLearnCourseApiResponse laCourseResponse = laCourseApiService.getLaLearnCourse(String.valueOf(courseRequest.getLaCourseParentId()));
        	laCourseApiService.updateParentAndRootId(course, courseRequest.getLaCourseParentId() , laCourseResponse.getLaCourseRootId());
        }
        else
        {
        	laCourseApiService.updateParentAndRootId(course, course.getLaCourseId() , course.getLaCourseId());
        }
        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "Course Created Successfully"));
    }
    
    @PostMapping("/updateLaLearnCourse")
    public ResponseEntity<?> updateLaLearnCourse(@Valid @RequestBody LaLearnCourseApiResponse courseRequest) {
    	LaLearnCourse course = laCourseApiService.updateLaLearnCourse(courseRequest);
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{courseId}")
                .buildAndExpand(course.getLaCourseId()).toUri();
        if(Objects.nonNull(courseRequest.getLaCourseParentId()))
        {
        	LaLearnCourseApiResponse laCourseResponse = laCourseApiService.getLaLearnCourse(String.valueOf(courseRequest.getLaCourseParentId()));
        	laCourseApiService.updateParentAndRootId(course, courseRequest.getLaCourseParentId() , laCourseResponse.getLaCourseRootId());
        }
        else
        {
        	laCourseApiService.updateParentAndRootId(course, course.getLaCourseId() , course.getLaCourseId());
        }
        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "Course Updated Successfully"));
    }
    
    @GetMapping("/deleteLaLearnCourse")
    public boolean deleteLaLearnCourse(@RequestParam(value = "key") String courseId) {
        laCourseApiService.deleteLaLearnCourse(courseId);
        return true;
    }
    
    @GetMapping("/searchLaLearnCourse")
    public LaLearnCourseApiResponse searchLaLearnCourse(@RequestParam(value = "searchKey") String searchKey) {
        return laCourseApiService.searchLaLearnCourse(searchKey);
    }

}
