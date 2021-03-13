package com.lernopus.lernopus.repository.api;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.QLaLearnCourse;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

/**
 * Created by amernath v on 2019-09-04.
 */
@Repository
public interface LaCourseApiRepository extends JpaRepository<LaLearnCourse, Long>, QuerydslPredicateExecutor<LaLearnCourse>, QuerydslBinderCustomizer<QLaLearnCourse> {
	
    @Query("select c from LaLearnCourse as c")
    Page<LaLearnCourse> getAllCourses(Pageable pageable);
    
    @Query(value="select c from LaLearnCourse as c "
    		+ "where c.:filterKey = :filterValue", nativeQuery = true)
    Page<LaLearnCourse> getAllCourseWithConditions(Pageable pageable,@Param("filterKey") String filterKey, @Param("filterValue") String filterValue);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCourseName like %:courseNameOrDescriptionOrContent% or "
    		+ "c.laCourseDescription like %:courseNameOrDescriptionOrContent% or c.laCourseContentText like %:courseNameOrDescriptionOrContent%")
    Optional<LaLearnCourse> searchCourse(@Param("courseNameOrDescriptionOrContent") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCourseId = :courseId")
    Optional<LaLearnCourse> getCourse(@Param("courseId") Long userId);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where :filterKey = :filterValue")
    Optional<LaLearnCourse> filterLaLearnCourse(@Param("filterKey") String filterKey, @Param("filterValue") String filterValue);
    
    @Override
    default public void customize(
      QuerydslBindings bindings, QLaLearnCourse root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        //bindings.excluding(root.email);
      }
}
