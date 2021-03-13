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
import com.lernopus.lernopus.model.audit.LaUserDateAudit;

/**
 * Created by amernath v on 2019-09-04.
 */
@Entity
@Table(name = "la_learn_course_rating")
public class LaLearnCourseRating  extends LaUserDateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7581751424346679543L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_rating_id")
    private Long laRatingId;

	@Column(name = "la_upvote_count")
    private Long laUpvoteCount;
    
    @Column(name = "la_downvote_count")
    private Long laDownvoteCount;
    
    @Column(name = "la_user_rating")
    private Long laUserRating;
    
    @Column(name = "la_no_of_views")
    private Long laNoOfViews;
    
    @Column(name = "la_user_id_reference")
    private Long laUserIdReference;
    
    @Column(name = "la_course_id_reference")
    private Long laCourseIdReference;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaLearnCourse laLearnCourse;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "la_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaLearnUser laLearnUser;
    
    public LaLearnCourseRating() {

    }

    public LaLearnCourseRating(Long laUpvoteCount, Long laDownvoteCount, Long laUserRating, Long laNoOfViews, LaLearnCourse laLearnCourse, LaLearnUser laLearnUser, Long laCourseIdReference, Long laUserIdReference) {
        this.laUpvoteCount = laUpvoteCount;
        this.laDownvoteCount = laDownvoteCount;
        this.laUserRating = laUserRating;
        this.laNoOfViews = laNoOfViews;
        this.laLearnCourse = laLearnCourse;
        this.laLearnUser = laLearnUser;
        this.laUserIdReference = laUserIdReference;
        this.laCourseIdReference = laCourseIdReference;
    }

    public Long getLaRatingId() {
        return laRatingId;
    }

    public void setLaRatingId(Long laRatingId) {
        this.laRatingId = laRatingId;
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
    
    public Long getLaNoOfViews() {
        return laNoOfViews;
    }

    public void setLaNoOfViews(Long laNoOfViews) {
        this.laNoOfViews = laNoOfViews;
    }
    
    public LaLearnCourse getLaLearnCourse() {
        return laLearnCourse;
    }

    public void setLaLearnCourse(LaLearnCourse laLearnCourse) {
        this.laLearnCourse = laLearnCourse;
    }
    
    public LaLearnUser getLaLearnUser() {
        return laLearnUser;
    }

    public void setLaLearnUser(LaLearnUser laLearnUser) {
        this.laLearnUser = laLearnUser;
    }
    
    public Long getLaUserIdReference() {
        return laUserIdReference;
    }

    public void setLaUserIdReference(Long laUserIdReference) {
        this.laUserIdReference = laUserIdReference;
    }

    
    public Long getLaCourseIdReference() {
        return laCourseIdReference;
    }

    public void setLaCourseIdReference(Long laCourseIdReference) {
        this.laCourseIdReference = laCourseIdReference;
    }


}
