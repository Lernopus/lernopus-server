package com.lernopus.lernopus.payload;

import java.util.Map;

public class LaSearchResponse {

    private Map<String,Object> laSearchCourseResults;
    
    private Map<String,Object> laSearchCourseCreatedByResults;
    
    private Map<String,Object> laSearchOfficialCategoryResults;
    
    private Map<String,Object> laSearchSpecialCategoryResults;
    
    private Map<String,Object> laSearchAuthorResults;
    
    private Map<String,Object> laSearchUserResults;
    
    public Map<String,Object> getLaSearchCourseResults() {
        return laSearchCourseResults;
    }

    public void setLaSearchCourseResults(Map<String,Object> laSearchCourseResults) {
        this.laSearchCourseResults = laSearchCourseResults;
    }
    
    public Map<String,Object> getLaSearchCourseCreatedByResults() {
        return laSearchCourseCreatedByResults;
    }

    public void setLaSearchCourseCreatedByResults(Map<String,Object> laSearchCourseCreatedByResults) {
        this.laSearchCourseCreatedByResults = laSearchCourseCreatedByResults;
    }

    
    public Map<String,Object> getLaSearchOfficialCategoryResults() {
        return laSearchOfficialCategoryResults;
    }

    public void setLaSearchOfficialCategoryResults(Map<String,Object> laSearchOfficialCategoryResults) {
        this.laSearchOfficialCategoryResults = laSearchOfficialCategoryResults;
    }
    
    public Map<String,Object> getLaSearchSpecialCategoryResults() {
        return laSearchSpecialCategoryResults;
    }

    public void setLaSearchSpecialCategoryResults(Map<String,Object> laSearchSpecialCategoryResults) {
        this.laSearchSpecialCategoryResults = laSearchSpecialCategoryResults;
    }
    
    public Map<String,Object> getLaSearchAuthorResults() {
        return laSearchAuthorResults;
    }

    public void setLaSearchAuthorResults(Map<String,Object> laSearchAuthorResults) {
        this.laSearchAuthorResults = laSearchAuthorResults;
    }
    
    public Map<String,Object> getLaSearchUserResults() {
        return laSearchUserResults;
    }

    public void setLaSearchUserResults(Map<String,Object> laSearchUserResults) {
        this.laSearchUserResults = laSearchUserResults;
    }

}
