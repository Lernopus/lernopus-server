package com.lernopus.lernopus.payload.api;

import java.util.HashSet;
import java.util.Set;
import com.lernopus.lernopus.model.AuthProvider;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnRole;

public class LaLearnUserApiResponse {

	private Long laUserId;
    private String laUserName;
    private String laUserFullName;
    private String laMailId;
    private Long laPhoneNumber;
    private String laPassword;
    private String laImagePath;
    private Boolean emailVerified = false;
    private AuthProvider provider;
    private String providerId;
    private Set<LaLearnRole> laRoles = new HashSet<>();
    private Set<LaLearnCourse> laAuthor = new HashSet<>();
    private Set<LaLearnCourse> laCourse = new HashSet<>();

    public LaLearnUserApiResponse() {

    }
    
    public LaLearnUserApiResponse(Long laUserId, String laUserName, String laUserFullName, String laMailId, Long laPhoneNumber, String laPassword, String laImagePath, Boolean emailVerified, AuthProvider provider, String providerId, Set<LaLearnRole> laRoles, Set<LaLearnCourse> laAuthor, Set<LaLearnCourse> laCourse) {
    	this.laUserId = laUserId;
    	this.laUserName = laUserName;
    	this.laUserFullName = laUserFullName;
    	this.laMailId = laMailId;
    	this.laPhoneNumber = laPhoneNumber;
    	this.laPassword = laPassword;
    	this.laImagePath = laImagePath;
    	this.emailVerified = emailVerified;
    	this.provider = provider;
    	this.providerId = providerId;
    	this.laRoles = laRoles;
    	this.laAuthor = laAuthor;
    	this.laCourse = laCourse;
    }
    
    public Long getLaUserId() {
        return laUserId;
    }

    public void setLaUserId(Long laUserId) {
        this.laUserId = laUserId;
    }

    public String getLaUserName() {
        return laUserName;
    }

    public void setLaUserName(String laUserName) {
        this.laUserName = laUserName;
    }

    public String getLaUserFullName() {
        return laUserFullName;
    }

    public void setLaUserFullName(String laUserFullName) {
        this.laUserFullName = laUserFullName;
    }

    public String getLaMailId() {
        return laMailId;
    }

    public void setLaMailId(String laMailId) {
        this.laMailId = laMailId;
    }
    
    public Long getLaPhoneNumber() {
        return laPhoneNumber;
    }

    public void setLaPhoneNumber(Long laPhoneNumber) {
        this.laPhoneNumber = laPhoneNumber;
    }
    
    public String getLaImagePath() {
        return laImagePath;
    }

    public void setLaImagePath(String laImagePath) {
        this.laImagePath = laImagePath;
    }


    public String getLaPassword() {
        return laPassword;
    }

    public void setLaPassword(String laPassword) {
        this.laPassword = laPassword;
    }

    public Set<LaLearnRole> getLaRoles() {
        return laRoles;
    }

    public void setLaRoles(Set<LaLearnRole> roles) {
        this.laRoles = roles;
    }
    
    public Set<LaLearnCourse> getLaAuthor() {
        return laAuthor;
    }

    public void setLaAuthor(Set<LaLearnCourse> laAuthor) {
        this.laAuthor = laAuthor;
    }
    
    public Set<LaLearnCourse> getLaCourse() {
        return laCourse;
    }

    public void setLaCourse(Set<LaLearnCourse> laCourse) {
        this.laCourse = laCourse;
    }
    
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    
    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
