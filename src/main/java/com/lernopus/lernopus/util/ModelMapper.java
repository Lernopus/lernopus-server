package com.lernopus.lernopus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.lernopus.lernopus.model.LaLearnAttachments;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaCategoryResponse;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.LaSearchResponse;
import com.lernopus.lernopus.payload.LaUserSummary;

public class ModelMapper {

    public static LaCourseResponse mapCourseToCourseResponse(LaLearnCourse course, LaLearnUser creator) {
        LaCourseResponse courseResponse = new LaCourseResponse();
        courseResponse.setLearnCourseId(course.getLaCourseId());
        courseResponse.setLaLearnCourseName(course.getLaCourseName());
        courseResponse.setLaCreatedAt(course.getLaCreatedAt());
        courseResponse.setLaCourseContentHtml(course.getLaCourseContentHtml());
        courseResponse.setLaCourseContentText(course.getLaCourseContentText());
        courseResponse.setLaIsNote(course.getLaIsNote());
        Set<LaLearnTechnology> techTagSet = course.getLaTechTag();
        List<String> techTagList = new ArrayList<>();
        techTagSet.stream().forEach(techTag ->{
        	techTagList.add(techTag.getName());
        });
        courseResponse.setLaTechTag(techTagList);
        List<LaLearnAttachments> laLearnAttachmentsList = course.getLaLearnAttachments();
        List<Map<String,Object>> attachParamList = new ArrayList<>();
        laLearnAttachmentsList.stream().forEach(learnAttach->{
        	Map<String,Object> attachParam = new HashMap<>();
        	attachParam.put("laAttachPreview", learnAttach.getLaAttachPreview());
        	attachParam.put("laAttachName", learnAttach.getLaAttachName());
        	attachParam.put("laAttachExtension", learnAttach.getLaAttachExtension());
        	attachParam.put("laAttachFileRefId", learnAttach.getLaAttachFileRefId());
        	attachParamList.add(attachParam);
        });
        courseResponse.setLaLearnAttachments(attachParamList);
        if(Objects.nonNull(creator))
        {
        	LaUserSummary creatorSummary = new LaUserSummary(creator.getLaUserId(), creator.getLaUserName(), creator.getLaUserFullName(),creator.getLaImagePath());
        	courseResponse.setCreatedBy(creatorSummary);
        }
        return courseResponse;
    }
    
    public static LaCategoryResponse mapCategoryToCategoryResponse(LaLearnTechnology category) {
        LaCategoryResponse categoryResponse = new LaCategoryResponse();
        categoryResponse.setLaTechId(category.getTechId());
        categoryResponse.setLaTechCategory(category.getLaTechCategory());
        categoryResponse.setLaTechGroup(category.getTechGroup());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
    
    public static LaUserSummary mapUserToUserResponse(LaLearnUser laLearnUser) {
    	LaUserSummary categoryResponse = new LaUserSummary(laLearnUser.getLaUserId(), laLearnUser.getLaUserName(), laLearnUser.getLaUserFullName(), laLearnUser.getLaImagePath());
        categoryResponse.setLaUserId(laLearnUser.getLaUserId());
        categoryResponse.setLaUserFullName(laLearnUser.getLaUserFullName());
        categoryResponse.setLaUserName(laLearnUser.getLaUserName());
        categoryResponse.setLaImagePath(laLearnUser.getLaImagePath());
        return categoryResponse;
    }

	public static LaSearchResponse mapCourseToSearchResponse(LaLearnCourse course) {
		LaSearchResponse laSearchResponse = new LaSearchResponse();
		Map<String,Object> laSearchMap = new HashMap<>();
		laSearchMap.put("value", course.getLaCourseId());
		laSearchMap.put("label", course.getLaCourseName());
		laSearchMap.put("type", "Courses");
		laSearchResponse.setLaSearchCourseResults(laSearchMap);
		return laSearchResponse;
	}
	
	public static LaSearchResponse mapCourseCreatedByToSearchResponse(LaLearnCourse course) {
		LaSearchResponse laSearchResponse = new LaSearchResponse();
		Map<String,Object> laSearchMap = new HashMap<>();
		laSearchMap.put("value", course.getLaCourseId());
		laSearchMap.put("label", course.getLaCourseName());
		laSearchMap.put("type", "Courses Created By");
		laSearchResponse.setLaSearchCourseResults(laSearchMap);
		return laSearchResponse;
	}

	public static LaSearchResponse mapOfficialCategoryToSearchResponse(LaLearnTechnology category) {
		LaSearchResponse laSearchResponse = new LaSearchResponse();
		Map<String,Object> laSearchMap = new HashMap<>();
		laSearchMap.put("value", category.getTechId());
		laSearchMap.put("label", category.getName());
		laSearchMap.put("type", "LearnOpus Official Categories");
		laSearchResponse.setLaSearchOfficialCategoryResults(laSearchMap);
		return laSearchResponse;
	}
	
	public static LaSearchResponse mapSpecialCategoryToSearchResponse(LaLearnTechnology category) {
		LaSearchResponse laSearchResponse = new LaSearchResponse();
		Map<String,Object> laSearchMap = new HashMap<>();
		laSearchMap.put("value", category.getTechId());
		laSearchMap.put("label", category.getName());
		laSearchMap.put("type", "LearnOpus Special Categories");
		laSearchResponse.setLaSearchSpecialCategoryResults(laSearchMap);
		return laSearchResponse;
	}
	
	public static LaSearchResponse mapAuthorToSearchResponse(LaLearnUser user) {
		LaSearchResponse laSearchResponse = new LaSearchResponse();
		Map<String,Object> laSearchMap = new HashMap<>();
		laSearchMap.put("value", user.getLaUserId());
		laSearchMap.put("label", user.getLaUserFullName());
		laSearchMap.put("type", "LearnOpus Authors");
		laSearchResponse.setLaSearchAuthorResults(laSearchMap);
		return laSearchResponse;
	}
	
	public static LaSearchResponse mapUserToSearchResponse(LaLearnUser user) {
		LaSearchResponse laSearchResponse = new LaSearchResponse();
		Map<String,Object> laSearchMap = new HashMap<>();
		laSearchMap.put("value", user.getLaUserId());
		laSearchMap.put("label", user.getLaUserFullName());
		laSearchMap.put("type", "LearnOpus Users");
		laSearchResponse.setLaSearchAuthorResults(laSearchMap);
		return laSearchResponse;
	}

}
