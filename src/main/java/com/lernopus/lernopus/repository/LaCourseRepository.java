package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by amernath v on 2019-09-05.
 */
@Repository
public interface LaCourseRepository extends JpaRepository<LaLearnCourse, Long> {

    Optional<LaLearnCourse> findByLaCourseId(Long courseId);

    Page<LaLearnCourse> findByLaCreatedUser(Long userId, Pageable pageable);

    long countByLaCreatedUser(Long userId);

    List<LaLearnCourse> findByLaCourseIdIn(List<Long> courseIds);

    List<LaLearnCourse> findByLaCourseIdIn(List<Long> courseIds, Sort sort);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCreatedUser = :userId and (c.laCourseName like %:searchedValue% or c.laCourseContentHtml like %:searchedValue%)  and c.laCourseParentId != 1 and c.laCourseParentId != 2")
    Page<LaLearnCourse> findByLaCreatedUserForSearch(@Param("userId") Long userId, Pageable pageable, @Param("searchedValue") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCourseId in (Select distinct cin.laCourseRootId from LaLearnCourse as cin)")
    Page<LaLearnCourse> getAllRootCourse(Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCourseId in (Select distinct cin.laCourseParentId from LaLearnCourse as cin)")
    Page<LaLearnCourse> getAllParentCourse(Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCourseId in (Select distinct cin.laCourseId from LaLearnCourse as cin where cin.laCourseId not in (Select distinct cin1.laCourseParentId from LaLearnCourse as cin1) and cin.laCourseId not in (Select distinct cin2.laCourseRootId from LaLearnCourse as cin2) and cin.laCourseParentId != 1 and cin.laCourseParentId != 2)")
    Page<LaLearnCourse> getAllChildCourse(Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where c.laCourseId in (Select distinct cin.laCourseId from LaLearnCourse as cin where cin.laCourseId not in (Select distinct cin1.laCourseParentId from LaLearnCourse as cin1) and cin.laCourseId not in (Select distinct cin2.laCourseRootId from LaLearnCourse as cin2) and cin.laCourseParentId != 1 and cin.laCourseParentId != 2) and (c.laCourseName like %:searchedValue% or c.laCourseContentHtml like %:searchedValue%)")
    Page<LaLearnCourse> getAllChildCourseForSearch(Pageable pageable, @Param("searchedValue") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnCourse as c "
    		+ "where (c.laCourseParentId = :parentCourseId or c.laCourseRootId = :parentCourseId) and c.laCourseId != :parentCourseId and c.laCourseParentId != 1 and c.laCourseParentId != 2")
    Page<LaLearnCourse> getAllChildCourse(@Param("parentCourseId") Long courseId, Pageable pageable);
    
    @Query("Select cin from LaLearnCourse as cin where :learnTechnology member of cin.laTechTag and cin.laCourseParentId != 1 and cin.laCourseParentId != 2")
    Page<LaLearnCourse> getAllCourseForCategory(@Param("learnTechnology") LaLearnTechnology learnTechnology, Pageable pageable);
    
    @Query("Select cin from LaLearnCourse as cin where :learnTechnology member of cin.laTechTag and cin.laCourseParentId != 1 and cin.laCourseParentId != 2 and (cin.laCourseName like %:searchedValue% or cin.laCourseContentHtml like %:searchedValue%)")
    Page<LaLearnCourse> getAllCourseForCategoryForSearch(@Param("learnTechnology") LaLearnTechnology learnTechnology, Pageable pageable, @Param("searchedValue") String searchedValue);

    @Query("Select cin from LaLearnCourse as cin where laCourseParentId != 1 and laCourseParentId != 2 and (laCourseName like %:searchedValue% or laCourseContentHtml like %:searchedValue%)")
	Page<LaLearnCourse> getSearchResultsForCourse(@Param("searchedValue") String searchedValue, Pageable pageable);
    
}
