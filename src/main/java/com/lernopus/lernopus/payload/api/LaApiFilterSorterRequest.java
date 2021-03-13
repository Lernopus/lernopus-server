package com.lernopus.lernopus.payload.api;

import java.util.Map;

public class LaApiFilterSorterRequest {

	private Map<String, Object> filterData;
	private Map<String, Object> sorterData;
	private String searchData;
	private Integer page;
	private Integer size;

    public LaApiFilterSorterRequest() {

    }
    
    public LaApiFilterSorterRequest(Map<String, Object> filterData, Map<String, Object> sorterData, String searchData, Integer page, Integer size) {
    	this.filterData = filterData;
    	this.sorterData = sorterData;
    	this.page = page;
    	this.size = size;
    	this.searchData = searchData;
    }
    
    public Map<String, Object> getFilterData() {
        return filterData;
    }

    public void setFilterData(Map<String, Object> filterData) {
        this.filterData = filterData;
    }
    
    public Map<String, Object> getSorterData() {
        return sorterData;
    }

    public void setSorterData(Map<String, Object> sorterData) {
        this.sorterData = sorterData;
    }
    
    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }
    
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
