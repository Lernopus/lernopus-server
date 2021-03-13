package com.lernopus.lernopus.util.api;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.api.LaLearnCourseApiResponse;
import com.lernopus.lernopus.payload.api.LaLearnUserApiResponse;

public class LaApiModelMapper {

    public static LaLearnUserApiResponse mapUserToApiUserResponse(LaLearnUser laLearnUser) {
    	LaLearnUserApiResponse laLearnUserApiResponse = new LaLearnUserApiResponse(laLearnUser.getLaUserId(), laLearnUser.getLaUserName(), laLearnUser.getLaUserFullName(),laLearnUser.getLaMailId(), laLearnUser.getLaPhoneNumber(), laLearnUser.getLaPassword(), laLearnUser.getLaImagePath(), laLearnUser.getEmailVerified(), laLearnUser.getProvider(),laLearnUser.getProviderId(), null, laLearnUser.getLaAuthor(), laLearnUser.getLaCourse());
        return laLearnUserApiResponse;
    }
    
    public static LaLearnCourseApiResponse mapCourseToApiCourseResponse(LaLearnCourse laLearnCourse) {
    	LaLearnCourseApiResponse laLearnCourseApiResponse = new LaLearnCourseApiResponse(laLearnCourse.getLaCourseId(), laLearnCourse.getLaCourseParentId(), laLearnCourse.getLaCourseRootId(), laLearnCourse.getLaCourseName(), laLearnCourse.getLaCourseDescription(), laLearnCourse.getLaCourseBackgroundImage(), laLearnCourse.getLaCourseContentText(), laLearnCourse.getLaCourseContentHtml(), laLearnCourse.getLaAuthorId(), laLearnCourse.getLaIsNote(), laLearnCourse.getLaAllowComment(), laLearnCourse.getLaAllowRating(), laLearnCourse.getLaWhatWillILearn(), laLearnCourse.getLaPrerequisite(), laLearnCourse.getLaUrlReference(), laLearnCourse.getLaSlideShowUrlReference(), laLearnCourse.getLaVideoUrlReference(), laLearnCourse.getAttachmentMeta(), laLearnCourse.getLaAuthorSubscription(), laLearnCourse.getLaCourseSubscription(), null, laLearnCourse.getLaLearnCourseComments(), laLearnCourse.getLaLearnCourseRating());
        return laLearnCourseApiResponse;
    }

}
