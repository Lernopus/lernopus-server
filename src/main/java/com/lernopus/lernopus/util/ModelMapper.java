package com.lernopus.lernopus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnCourseComments;
import com.lernopus.lernopus.model.LaLearnCourseRating;
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
        courseResponse.setLaCourseDescription(course.getLaCourseDescription());
        courseResponse.setLaCreatedAt(course.getLaCreatedAt());
        courseResponse.setLaCourseContentHtml(course.getLaCourseContentHtml());
        courseResponse.setLaCourseContentText(course.getLaCourseContentText());
        courseResponse.setLaIsNote(course.getLaIsNote());
        courseResponse.setLaAllowComment(course.getLaAllowComment());
        courseResponse.setLaAllowRating(course.getLaAllowRating());
        courseResponse.setLaCourseBackgroundImage(course.getLaCourseBackgroundImage());
        courseResponse.setLaWhatWillILearn(course.getLaWhatWillILearn());
        courseResponse.setLaPrerequisite(course.getLaPrerequisite());
        courseResponse.setLaUrlReference(course.getLaUrlReference());
        courseResponse.setLaSlideShowUrlReference(course.getLaSlideShowUrlReference());
        courseResponse.setLaVideoUrlReference(course.getLaVideoUrlReference());
        courseResponse.setAttachmentMeta(course.getAttachmentMeta());
        Set<LaLearnTechnology> techTagSet = course.getLaTechTag();
        List<String> techTagList = new ArrayList<>();
        techTagSet.stream().forEach(techTag ->{
        	techTagList.add(techTag.getName());
        });
        courseResponse.setLaTechTag(techTagList);
        
        List<LaLearnCourseComments> laLearnCommentList = course.getLaLearnCourseComments();
        List<LaLearnCourseRating> laLearnCourseRatingList = course.getLaLearnCourseRating();
        List<Map<String,Object>> commentParamParamList = new ArrayList<>();
        laLearnCommentList.stream().forEach(learnComment ->{
        	Map<String,Object> commentParam = new HashMap<>();
        	commentParam.put("laCommentId", learnComment.getLaCommentId());
        	commentParam.put("laCommentContent", learnComment.getLaCommentContent());
        	commentParamParamList.add(commentParam);
        });
        courseResponse.setLaLearnCourseComments(commentParamParamList);
        List<Map<String,Object>> ratingParamParamList = new ArrayList<>();
        AtomicLong overallRatingValue = new AtomicLong(0L);
        AtomicInteger overallRatingCount = new AtomicInteger(0);
        laLearnCourseRatingList.stream().forEach(learnCourseRating ->{
        	Map<String,Object> ratingParam = new HashMap<>();
        	ratingParam.put("laUserId", learnCourseRating.getLaLearnUser().getLaUserId());
        	ratingParam.put("laUserRating", learnCourseRating.getLaUserRating());
        	ratingParam.put("laUpvoteCount", learnCourseRating.getLaUpvoteCount());
        	ratingParam.put("laDownvoteCount", learnCourseRating.getLaDownvoteCount());
        	ratingParam.put("laRatingId", learnCourseRating.getLaRatingId());
        	overallRatingValue.set(overallRatingValue.get() + learnCourseRating.getLaUserRating());
        	overallRatingCount.set(overallRatingCount.addAndGet(1));
        	ratingParamParamList.add(ratingParam);
        });
        
        if(overallRatingValue.get() > 0 && overallRatingCount.get() > 0) {
        	overallRatingValue.set(overallRatingValue.get() / overallRatingCount.get());
        }
        courseResponse.setLaLearnCourseRating(ratingParamParamList);
        courseResponse.setLaLearnCourseOverallRating(overallRatingValue.get());
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
