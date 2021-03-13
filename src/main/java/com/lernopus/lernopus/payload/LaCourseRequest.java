package com.lernopus.lernopus.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.lernopus.lernopus.model.LaLearnTechnology;

import java.util.Set;

public class LaCourseRequest {
    @NotBlank
    @Size(max = 250)
    private String laCourseName;
    
    @Valid
    private String laCourseDescription;
    
    @Valid
    private String laCourseBackgroundImage;
    
    @Valid
    private Boolean laAllowComment;
    
    @Valid
    private Boolean laAllowRating;
    
    @Valid
    @NotNull
    private String laCourseContentText;
    
    @Valid
    @NotNull
    private String laCourseContentHtml;
    
    @Size(max = 8)
    @Valid
    @NotNull
    private String laAuthorId;
    
    @Valid
    @NotNull
    private Boolean laIsNote;
    
    @Valid
    @NotNull
    private Set<LaLearnTechnology> laTechTag;
    
    @Valid
    private Long laCourseParentId;
    
    @Valid
    private Long laCourseRootId;
    
    @Valid
    private String laWhatWillILearn;
    
    @Valid
    private String laPrerequisite;
    
    @Valid
    private String laUrlReference;
    
    @Valid
    private String laSlideShowUrlReference;
    
    @Valid
    private String laVideoUrlReference;
    
    @Valid
	private String attachmentMeta;
    
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
    
    public Set<LaLearnTechnology> getLaTechTag() {
        return laTechTag;
    }

    public void setLaTechTag(Set<LaLearnTechnology> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public String getLaCourseContentHtml() {
        return laCourseContentHtml;
    }

    public void setLaCourseContentHtml(String laCourseContentHtml) {
        this.laCourseContentHtml = laCourseContentHtml;
    }
    
    public String getLaCourseContentText() {
        return laCourseContentText;
    }

    public void setLaCourseContentText(String laCourseContentText) {
        this.laCourseContentText = laCourseContentText;
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
