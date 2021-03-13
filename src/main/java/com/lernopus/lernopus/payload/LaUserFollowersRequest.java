package com.lernopus.lernopus.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class LaUserFollowersRequest {
    
	@Valid
	@NotBlank
    private String laUserId;
    
    @Valid
    @NotBlank
    private String laUserFollowerId;
    
        
    public String getLaUserId() {
        return laUserId;
    }

    public void setLaUserId(String laUserId) {
        this.laUserId = laUserId;
    }
    
    public String getLaUserFollowerId() {
        return laUserFollowerId;
    }

    public void setLaUserFollowerId(String laUserFollowerId) {
        this.laUserFollowerId = laUserFollowerId;
    }
        
}
