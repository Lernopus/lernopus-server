package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnCourseComments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaCourseCommentRepository extends JpaRepository<LaLearnCourseComments, String> {

}
