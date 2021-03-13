package com.lernopus.lernopus.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.lernopus.lernopus.model.LaRoleName;

public class LaCourseViewRequest {
    
    @Valid
    @NotBlank
    private String laCourseId;
      
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
    
    public LaCourseViewRequest(String laCourseId , String laUserId) {
        this.laCourseId = laCourseId;
        this.laUserId = laUserId;
    }

}
