package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnCourseRating;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LaCourseRatingRepository extends JpaRepository<LaLearnCourseRating, String> {

	@Query("select c  "
    		+ "from LaLearnCourseRating as c "
    		+ "where c.laCourseIdReference = :courseId"
    		+ " and c.laUserIdReference = :userId")
	Page<LaLearnCourseRating> findByUserAndCourse(@Param("courseId") Long courseId, @Param("userId") Long userId, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("update LaLearnCourseRating as c "
    		+ "set c.laUserRating = :userRating, c.laUpvoteCount = :upVoteCount, c.laDownvoteCount = :downVoteCount"
    		+ " where c.laCourseIdReference = :courseId"
    		+ " and c.laUserIdReference = :userId")
	void updateRatingByUserOnCourse(@Param("courseId") Long courseId, @Param("userId") Long userId, @Param("userRating") Long userRating,  @Param("upVoteCount") Long upVoteCount, @Param("downVoteCount") Long downVoteCount);
	
	@Transactional
	@Modifying
	@Query("update LaLearnCourseRating as c "
    		+ "set c.laNoOfViews = :userViews"
    		+ " where c.laCourseIdReference = :courseId"
    		+ " and c.laUserIdReference = :userId")
	void updateViewsByUserOnCourse(@Param("courseId") Long courseId, @Param("userId") Long userId, @Param("userViews") Long userViews);
}
