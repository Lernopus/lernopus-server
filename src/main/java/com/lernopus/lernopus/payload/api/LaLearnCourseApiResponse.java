package com.lernopus.lernopus.payload.api;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.lernopus.lernopus.model.LaLearnCourseComments;
import com.lernopus.lernopus.model.LaLearnCourseRating;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;

public class LaLearnCourseApiResponse {
    
    private Long laCourseId;
    private Long laCourseParentId;
    private Long laCourseRootId;
    private String laCourseName;
    private String laCourseDescription;
    private String laCourseBackgroundImage;
    private String laCourseContentText;
    private String laCourseContentHtml;
    private String laAuthorId;
    private Boolean laIsNote;
    private Boolean laAllowComment;
    private Boolean laAllowRating;
    private String laWhatWillILearn;
    private String laPrerequisite;
    private String laUrlReference;
    private String laSlideShowUrlReference;
    private String laVideoUrlReference;
    private String attachmentMeta;
    private Set<LaLearnUser> laAuthorSubscription = new HashSet<>();
    private Set<LaLearnUser> laCourseSubscription = new HashSet<>();
    private Set<LaLearnTechnology> laTechTag = new HashSet<>();
    private List<LaLearnCourseComments> laLearnCourseComments = new LinkedList<>();
    private List<LaLearnCourseRating> laLearnCourseRating = new LinkedList<>();

    public LaLearnCourseApiResponse() {

    }
    
    public LaLearnCourseApiResponse(Long laCourseId, Long laCourseParentId, Long laCourseRootId, String laCourseName, String laCourseDescription, String laCourseBackgroundImage, String laCourseContentText, String laCourseContentHtml, String laAuthorId, Boolean laIsNote, Boolean laAllowComment, Boolean laAllowRating, String laWhatWillILearn, String laPrerequisite, String laUrlReference, String laSlideShowUrlReference, String laVideoUrlReference, String attachmentMeta, Set<LaLearnUser> laAuthorSubscription, Set<LaLearnUser> laCourseSubscription, Set<LaLearnTechnology> laTechTag, List<LaLearnCourseComments> laLearnCourseComments, List<LaLearnCourseRating> laLearnCourseRating) {
    	this.laCourseId = laCourseId;
    	this.laCourseParentId = laCourseParentId;
    	this.laCourseRootId = laCourseRootId;
    	this.laCourseName = laCourseName;
    	this.laCourseDescription = laCourseDescription;
    	this.laCourseBackgroundImage = laCourseBackgroundImage;
    	this.laCourseContentText = laCourseContentText;
    	this.laCourseContentHtml = laCourseContentHtml;
    	this.laAuthorId = laAuthorId;
    	this.laIsNote = laIsNote;
    	this.laAllowComment = laAllowComment;
    	this.laAllowRating = laAllowRating;
    	this.laWhatWillILearn = laWhatWillILearn;
    	this.laPrerequisite = laPrerequisite;
    	this.laUrlReference = laUrlReference;
    	this.laSlideShowUrlReference = laSlideShowUrlReference;
    	this.laVideoUrlReference = laVideoUrlReference;
    	this.attachmentMeta = attachmentMeta;
    	this.laAuthorSubscription = laAuthorSubscription;
    	this.laCourseSubscription = laCourseSubscription;
    	this.laTechTag = laTechTag;
    	this.laLearnCourseComments = laLearnCourseComments;
    	this.laLearnCourseRating = laLearnCourseRating;  
    }

    public Long getLaCourseId() {
        return laCourseId;
    }

    public void setLaCourseId(Long laCourseId) {
        this.laCourseId = laCourseId;
    }
    
    public Long getLaCourseParentId() {
        return laCourseParentId;
    }

    public void setLaCourseParentId(Long laCourseParentId) {
        this.laCourseParentId = laCourseParentId;
    }
    
    public Long getLaCourseRootId() {
        return laCourseRootId;
    }

    public void setLaCourseRootId(Long laCourseRootId) {
        this.laCourseRootId = laCourseRootId;
    }

    public String getLaCourseName() {
        return laCourseName;
    }

    public void setLaCourseName(String laCourseName) {
        this.laCourseName = laCourseName;
    }
    
    public String getLaCourseDescription() {
        return laCourseDescription;
    }

    public void setLaCourseDescription(String laCourseDescription) {
        this.laCourseDescription = laCourseDescription;
    }
    
    public String getLaCourseBackgroundImage() {
        return laCourseBackgroundImage;
    }

    public void setLaCourseBackgroundImage(String laCourseBackgroundImage) {
        this.laCourseBackgroundImage = laCourseBackgroundImage;
    }
    
    public String getLaCourseContentText() {
        return laCourseContentText;
    }

    public void setLaCourseContentText(String laCourseContentText) {
        this.laCourseContentText = laCourseContentText;
    }
    
    public String getLaCourseContentHtml() {
        return laCourseContentHtml;
    }

    public void setLaCourseContentHtml(String laCourseContentHtml) {
        this.laCourseContentHtml = laCourseContentHtml;
    }


    public String getLaAuthorId() {
        return laAuthorId;
    }

    public void setLaAuthorId(String laAuthorId) {
        this.laAuthorId = laAuthorId;
    }

    public Boolean getLaIsNote() {
        return laIsNote;
    }

    public void setLaIsNote(Boolean laIsNote) {
        this.laIsNote = laIsNote;
    }
    
    public Boolean getLaAllowComment() {
        return laAllowComment;
    }

    public void setLaAllowComment(Boolean laAllowComment) {
        this.laAllowComment = laAllowComment;
    }
    
    public Boolean getLaAllowRating() {
        return laAllowRating;
    }

    public void setLaAllowRating(Boolean laAllowRating) {
        this.laAllowRating = laAllowRating;
    }
    
    public Set<LaLearnUser> getLaAuthorSubscription() {
        return laAuthorSubscription;
    }
    
    public void setLaAuthorSubscription(Set<LaLearnUser> laAuthorSubscription) {
        this.laAuthorSubscription = laAuthorSubscription;
    }
    
    public Set<LaLearnUser> getLaCourseSubscription() {
        return laCourseSubscription;
    }
    
    public void setLaCourseSubscription(Set<LaLearnUser> laCourseSubscription) {
        this.laCourseSubscription = laCourseSubscription;
    }
    
    public Set<LaLearnTechnology> getLaTechTag() {
        return laTechTag;
    }
    
    public void setLaTechTag(Set<LaLearnTechnology> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public String getLaWhatWillILearn() {
        return laWhatWillILearn;
    }

    public void setLaWhatWillILearn(String laWhatWillILearn) {
        this.laWhatWillILearn = laWhatWillILearn;
    }
    
    public String getLaPrerequisite() {
        return laPrerequisite;
    }

    public void setLaPrerequisite(String laPrerequisite) {
        this.laPrerequisite = laPrerequisite;
    }
    
    public String getLaUrlReference() {
        return laUrlReference;
    }

    public void setLaUrlReference(String laUrlReference) {
        this.laUrlReference = laUrlReference;
    }

    public String getLaSlideShowUrlReference() {
        return laSlideShowUrlReference;
    }

    public void setLaSlideShowUrlReference(String laSlideShowUrlReference) {
        this.laSlideShowUrlReference = laSlideShowUrlReference;
    }
    
    public String getLaVideoUrlReference() {
        return laVideoUrlReference;
    }

    public void setLaVideoUrlReference(String laVideoUrlReference) {
        this.laVideoUrlReference = laVideoUrlReference;
    }
    
    public String getAttachmentMeta() {
        return attachmentMeta;
    }

    public void setAttachmentMeta(String attachmentMeta) {
        this.attachmentMeta = attachmentMeta;
    }
    
    public List<LaLearnCourseComments> getLaLearnCourseComments() {
        return laLearnCourseComments;
    }
    
    public void setLaLearnCourseComments(List<LaLearnCourseComments> laLearnCourseComments) {
        this.laLearnCourseComments = laLearnCourseComments;
    }
    
    public List<LaLearnCourseRating> getLaLearnCourseRating() {
        return laLearnCourseRating;
    }
    
    public void setLaLearnCourseRating(List<LaLearnCourseRating> laLearnCourseRating) {
        this.laLearnCourseRating = laLearnCourseRating;
    }

}
