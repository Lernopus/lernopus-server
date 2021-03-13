package com.lernopus.lernopus.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class LaCourseCommentsRequest {
    
	@Valid
    private String laCourseCommentsContent;
    
    @Valid
    @NotBlank
    private String laCourseId;
    
        
    public String getLaCourseCommentsContent() {
        return laCourseCommentsContent;
    }

    public void setLaCourseCommentsContent(String laCourseCommentsContent) {
        this.laCourseCommentsContent = laCourseCommentsContent;
    }
    
    public String getLaCourseId() {
        return laCourseId;
    }

    public void setLaCourseId(String laCourseId) {
        this.laCourseId = laCourseId;
    }
        
}
