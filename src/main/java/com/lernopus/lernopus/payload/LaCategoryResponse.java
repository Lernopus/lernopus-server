package com.lernopus.lernopus.payload;

public class LaCategoryResponse {
    
	private Long laTechId;
    private String laTechCategory;
    private String laTechGroup;
    private String id;
    private String name;
    private PagedResponse<LaCategoryResponse> childCategoryPageResponse;

    public Long getTechId() {
        return laTechId;
    }

    public void setLaTechId(Long laTechId) {
        this.laTechId = laTechId;
    }

    public String getTechGroup() {
        return laTechGroup;
    }

    public void setLaTechGroup(String laTechGroup) {
        this.laTechGroup = laTechGroup;
    }
    
    public String getLaTechCategory() {
        return laTechCategory;
    }
    
    public void setLaTechCategory(String laTechCategory) {
        this.laTechCategory = laTechCategory;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public PagedResponse<LaCategoryResponse> getChildCategoryPageResponse() {
        return childCategoryPageResponse;
    }

    public void setChildCoursePageResponse(PagedResponse<LaCategoryResponse> childCategoryPageResponse) {
        this.childCategoryPageResponse = childCategoryPageResponse;
    }
}
