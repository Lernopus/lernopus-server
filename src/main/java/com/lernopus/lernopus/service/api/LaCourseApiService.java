package com.lernopus.lernopus.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.payload.api.LaApiFilterSorterRequest;
import com.lernopus.lernopus.payload.api.LaLearnCourseApiResponse;
import com.lernopus.lernopus.repository.api.LaCourseApiRepository;
import com.lernopus.lernopus.repository.predicate.LaApiPredicateBuilder;
import com.lernopus.lernopus.util.api.LaApiModelMapper;
import com.lernopus.lernopus.util.api.LaApiUtils;

@Service
public class LaCourseApiService {

    @Autowired
    private LaCourseApiRepository laCourseApiRepository;
            
    @SuppressWarnings("unchecked")
	public PagedResponse<LaLearnCourseApiResponse> getAllWithFilterLaLearnCourse(int page, int size, LaApiFilterSorterRequest laApiFilterSorterRequest) {
    	LaApiUtils.validatePageNumberAndSize(page, size);
    	Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laCourseId");
    	if(Objects.nonNull(laApiFilterSorterRequest) && Objects.nonNull(laApiFilterSorterRequest.getSorterData()) && !laApiFilterSorterRequest.getSorterData().isEmpty() && Objects.nonNull(laApiFilterSorterRequest.getSorterData().get("field")) && Objects.nonNull(laApiFilterSorterRequest.getSorterData().get("order"))) {
    		Direction sortType = Sort.Direction.DESC;
    		if(String.valueOf(laApiFilterSorterRequest.getSorterData().get("order")).equalsIgnoreCase("ascend")) {
    			sortType = Sort.Direction.ASC;
    		}
    		pageable = PageRequest.of(page, size, sortType, String.valueOf(laApiFilterSorterRequest.getSorterData().get("field")));	
    	}
        Page<LaLearnCourse> courses = null;
        List<String> filterKeyList = new ArrayList<>();
        if(Objects.nonNull(laApiFilterSorterRequest) && ((Objects.nonNull(laApiFilterSorterRequest.getFilterData()) && !laApiFilterSorterRequest.getFilterData().isEmpty()) || (Objects.nonNull(laApiFilterSorterRequest.getSearchData()) && !laApiFilterSorterRequest.getSearchData().isEmpty()))) {
        	LaApiPredicateBuilder builder = new LaApiPredicateBuilder();
        	if(Objects.nonNull(laApiFilterSorterRequest) && Objects.nonNull(laApiFilterSorterRequest.getFilterData()) && !laApiFilterSorterRequest.getFilterData().isEmpty()) {
        		laApiFilterSorterRequest.getFilterData().entrySet().stream().forEach(filterData -> {
            		List<Object> filterValueList = (List<Object>)filterData.getValue();
            		if(Objects.nonNull(filterValueList) && !filterValueList.isEmpty() && Objects.nonNull(filterValueList.get(0))) {
            			if(filterValueList.get(0) instanceof List<?>) {
            				List<Object> filterDataList = (List<Object>)filterValueList.get(0);
            				filterDataList.stream().forEach(dataList -> {
            					builder.with(filterData.getKey(), ":", dataList);
            				});
            				filterKeyList.add(filterData.getKey());
            			} else {
            				builder.with(filterData.getKey(), ":", filterValueList.get(0));
            				filterKeyList.add(filterData.getKey());
            			}
            		}
        		});	
        	}
        	if(Objects.nonNull(laApiFilterSorterRequest) && Objects.nonNull(laApiFilterSorterRequest.getSearchData()) && !laApiFilterSorterRequest.getSearchData().isEmpty()) {
        		if(!filterKeyList.contains("laCourseId")) {
        			builder.withOr("laCourseId", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laCourseParentId")) {
        			builder.withOr("laCourseParentId", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laCourseRootId")) {
        			builder.withOr("laCourseRootId", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laCourseName")) {
        			builder.withOr("laCourseName", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laAuthorId")) {
        			builder.withOr("laAuthorId", ":", laApiFilterSorterRequest.getSearchData());
        		}
        	}
        	courses = laCourseApiRepository.findAll(builder.build("laLearnCourse", Arrays.asList("laCourseId", "laCourseParentId", "laCourseRootId")), pageable);	
    	} else {
    		courses = laCourseApiRepository.getAllCourses(pageable);	
    	}
        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
            		courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        List<LaLearnCourseApiResponse> courseResponses = courses.map(course -> {
            return LaApiModelMapper.mapCourseToApiCourseResponse(course);
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
        		courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }
    
    public LaLearnCourseApiResponse getLaLearnCourse(String courseId) {
    	Optional<LaLearnCourse> course = laCourseApiRepository.getCourse(Long.valueOf(courseId));
        if(course.isPresent()) {
        	return LaApiModelMapper.mapCourseToApiCourseResponse(course.get());
        } else {
        	return null;
        }
    }
    
    public LaLearnCourseApiResponse searchLaLearnCourse(String courseNameOrDescriptionOrContent) {
    	Optional<LaLearnCourse> course = laCourseApiRepository.searchCourse(courseNameOrDescriptionOrContent);
        if(course.isPresent()) {
        	return LaApiModelMapper.mapCourseToApiCourseResponse(course.get());
        } else {
        	return null;
        }
    }
    
    public LaLearnCourse insertLaLearnCourse(LaLearnCourseApiResponse courseRequest) {
    	LaLearnCourse course = new LaLearnCourse();
        course.setLaCourseName(courseRequest.getLaCourseName());
        course.setLaCourseDescription(courseRequest.getLaCourseDescription());
        course.setLaCourseBackgroundImage(courseRequest.getLaCourseBackgroundImage());
        course.setLaCourseContentHtml(courseRequest.getLaCourseContentHtml());
        course.setLaCourseContentText(courseRequest.getLaCourseContentText());
        course.setLaIsNote(courseRequest.getLaIsNote());
        course.setLaAuthorId(courseRequest.getLaAuthorId());
        course.setLaTechTag(courseRequest.getLaTechTag());
        course.setLaAllowComment(courseRequest.getLaAllowComment());
        course.setLaAllowRating(courseRequest.getLaAllowRating());
        course.setLaWhatWillILearn(courseRequest.getLaWhatWillILearn());
        course.setLaPrerequisite(courseRequest.getLaPrerequisite());
        course.setLaUrlReference(courseRequest.getLaUrlReference());
        course.setLaSlideShowUrlReference(courseRequest.getLaSlideShowUrlReference());
        course.setLaVideoUrlReference(courseRequest.getLaVideoUrlReference());
        course.setAttachmentMeta(courseRequest.getAttachmentMeta());
        return laCourseApiRepository.save(course);
    }
    
    public LaLearnCourse updateLaLearnCourse(LaLearnCourseApiResponse courseRequest) {
    	Optional<LaLearnCourse> courseOptional = laCourseApiRepository.findById(Long.valueOf(courseRequest.getLaCourseId()));
    	if(courseOptional.isPresent()) {
    		LaLearnCourse course = 	courseOptional.get();
    		course.setLaCourseName(courseRequest.getLaCourseName());
            course.setLaCourseDescription(courseRequest.getLaCourseDescription());
            course.setLaCourseBackgroundImage(courseRequest.getLaCourseBackgroundImage());
            course.setLaCourseContentHtml(courseRequest.getLaCourseContentHtml());
            course.setLaCourseContentText(courseRequest.getLaCourseContentText());
            course.setLaIsNote(courseRequest.getLaIsNote());
            course.setLaAuthorId(courseRequest.getLaAuthorId());
            course.setLaTechTag(courseRequest.getLaTechTag());
            course.setLaAllowComment(courseRequest.getLaAllowComment());
            course.setLaAllowRating(courseRequest.getLaAllowRating());
            course.setLaWhatWillILearn(courseRequest.getLaWhatWillILearn());
            course.setLaPrerequisite(courseRequest.getLaPrerequisite());
            course.setLaUrlReference(courseRequest.getLaUrlReference());
            course.setLaSlideShowUrlReference(courseRequest.getLaSlideShowUrlReference());
            course.setLaVideoUrlReference(courseRequest.getLaVideoUrlReference());
            course.setAttachmentMeta(courseRequest.getAttachmentMeta());
            return laCourseApiRepository.save(course);
    	} else {
            return insertLaLearnCourse(courseRequest);
    	}
    }
    
    public void deleteLaLearnCourse(String courseId) {
    	laCourseApiRepository.deleteById(Long.valueOf(courseId));
    }

    public LaLearnCourseApiResponse filterLaLearnCourse(String filterKey, String filterValue) {
    	Optional<LaLearnCourse> course = laCourseApiRepository.filterLaLearnCourse(filterKey, filterValue);
        if(course.isPresent()) {
        	return LaApiModelMapper.mapCourseToApiCourseResponse(course.get());
        } else {
        	return null;
        }
    }
    
    public LaLearnCourse updateParentAndRootId(LaLearnCourse course, Long laCourseParentId, Long laCourseRootId) {
    	course.setLaCourseParentId(laCourseParentId);
    	course.setLaCourseRootId(laCourseRootId);
        return laCourseApiRepository.save(course);
    }

}
