package com.lernopus.lernopus.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class LaCourseRatingRequest {
    
    @Valid
    @NotBlank
    private String laCourseId;
    
    @Valid
    private Long laUpvoteCount;
    
    @Valid
    private Long laDownvoteCount;
    
    @Valid
    private Long laUserRating;
    
    @Valid
    @NotBlank
    private String laUserId;
    
    public String getLaCourseId() {
        return laCourseId;
    }

    public void setLaCourseId(String laCourseId) {
        this.laCourseId = laCourseId;
    }
    
    public String getLaUserId() {
        return laUserId;
    }

    public void setLaUserId(String laUserId) {
        this.laUserId = laUserId;
    }
    
    public Long getLaUpvoteCount() {
        return laUpvoteCount;
    }

    public void setLaUpvoteCount(Long laUpvoteCount) {
        this.laUpvoteCount = laUpvoteCount;
    }

    public Long getLaDownvoteCount() {
        return laDownvoteCount;
    }

    public void setLaDownvoteCount(Long laDownvoteCount) {
        this.laDownvoteCount = laDownvoteCount;
    }
    
    public Long getLaUserRating() {
        return laUserRating;
    }

    public void setLaUserRating(Long laUserRating) {
        this.laUserRating = laUserRating;
    }

}
