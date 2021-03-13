package com.lernopus.lernopus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by amernath v on 2019-09-04.
 */
@Entity
@Table(name = "la_learn_user_followers")
public class LaLearnUserFollowers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_followers_id")
    private Long laFollowersId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_user_id", nullable = false, referencedColumnName = "la_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaLearnUser laLearnUser;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_user_follower_id", nullable = false, referencedColumnName = "la_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaLearnUser laLearnFollowingUser;
    
    public LaLearnUserFollowers() {

    }

    public LaLearnUserFollowers(LaLearnUser laLearnUser, LaLearnUser laLearnFollowingUser) {
    	this.laLearnUser = laLearnUser;
    	this.laLearnFollowingUser = laLearnFollowingUser;
    }

    public Long getLaFollowersId() {
        return laFollowersId;
    }

    public void setLaFollowersId(Long laFollowersId) {
        this.laFollowersId = laFollowersId;
    }
    
    public LaLearnUser getLaLearnUser() {
        return laLearnUser;
    }
    
    public void setLaLearnUser(LaLearnUser laLearnUser) {
        this.laLearnUser = laLearnUser;
    }
    
    public LaLearnUser getLaLearnFollowingUser() {
        return laLearnFollowingUser;
    }
    
    public void setLaLearnFollowingUser(LaLearnUser laLearnFollowingUser) {
        this.laLearnFollowingUser = laLearnFollowingUser;
    }

}
