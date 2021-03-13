package com.lernopus.lernopus.payload;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LaCourseResponse {
    private Long laLearnCourseId;
    private String laLearnCourseName;
    private String laCourseDescription;
    private List<Map<String,Object>> laLearnAttachments;
    private String laCourseContentText;
    private String laCourseContentHtml;
    private List<String> laTechTag;
    private LaUserSummary createdBy;
    private Instant laCreatedAt;
    private Boolean laIsNote;
    private Boolean laAllowComment;
    private Boolean laAllowRating;
    private Long laCourseParentId;
    private Long laCourseRootId;
    private String laCourseBackgroundImage;
    private String laWhatWillILearn;
    private String laPrerequisite;
    private String laUrlReference;
    private String laSlideShowUrlReference;
    private String laVideoUrlReference;
    private String attachmentMeta;
    private List<Map<String,Object>> laLearnCourseComments;
    private List<Map<String,Object>> laLearnCourseRating;
    private Long laLearnCourseOverallRating;
    private PagedResponse<LaCourseResponse> childCoursePageResponse;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoice;
    private Long totalVotes;

    public Long getLearnCourseId() {
        return laLearnCourseId;
    }

    public void setLearnCourseId(Long laLearnCourseId) {
        this.laLearnCourseId = laLearnCourseId;
    }

    public String getLaLearnCourseName() {
        return laLearnCourseName;
    }

    public void setLaLearnCourseName(String laLearnCourseName) {
        this.laLearnCourseName = laLearnCourseName;
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
    
    public List<Map<String, Object>> getLaLearnAttachments() {
        return laLearnAttachments;
    }

    public void setLaLearnAttachments(List<Map<String, Object>> laLearnAttachments) {
        this.laLearnAttachments = laLearnAttachments;
    }
    
    public List<Map<String, Object>> getLaLearnCourseComments() {
        return laLearnCourseComments;
    }

    public void setLaLearnCourseComments(List<Map<String, Object>> laLearnCourseComments) {
        this.laLearnCourseComments = laLearnCourseComments;
    }
    
    public List<Map<String, Object>> getLaLearnCourseRating() {
        return laLearnCourseRating;
    }

    public void setLaLearnCourseRating(List<Map<String, Object>> laLearnCourseRating) {
        this.laLearnCourseRating = laLearnCourseRating;
    }
    
    public Long getLaLearnCourseOverallRating() {
        return laLearnCourseOverallRating;
    }

    public void setLaLearnCourseOverallRating(Long laLearnCourseOverallRating) {
        this.laLearnCourseOverallRating = laLearnCourseOverallRating;
    }

    public LaUserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LaUserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getExpired() {
        return laIsNote;
    }

    public void setExpired(Boolean expired) {
        laIsNote = expired;
    }

    public Long getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(Long selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }
    
    public Instant getLaCreatedAt() {
        return laCreatedAt;
    }

    public void setLaCreatedAt(Instant laCreatedAt) {
        this.laCreatedAt = laCreatedAt;
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
    
    public List<String> getLaTechTag() {
        return laTechTag;
    }
    
    public void setLaTechTag(List<String> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public Boolean getLaIsNote() {
        return laIsNote;
    }

    public void setLaIsNote(Boolean laIsNote) {
        this.laIsNote = laIsNote;
    }
    
    public Boolean getlaAllowComment() {
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
    
    public Long getCourseParentId() {
        return laCourseParentId;
    }

    public void setCourseParentId(Long laCourseParentId) {
        this.laCourseParentId = laCourseParentId;
    }
    
    public Long getCourseRootId() {
        return laCourseRootId;
    }

    public void setCourseRootId(Long laCourseRootId) {
        this.laCourseRootId = laCourseRootId;
    }
    
    public PagedResponse<LaCourseResponse> getChildCoursePageResponse() {
        return childCoursePageResponse;
    }

    public void setChildCoursePageResponse(PagedResponse<LaCourseResponse> childCoursePageResponse) {
        this.childCoursePageResponse = childCoursePageResponse;
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

}
