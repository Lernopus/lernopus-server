package com.lernopus.lernopus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnTechnology;

/**
 * Created by amernath v on 2019-09-05.
 */
@Repository
public interface LaCategoryRepository extends JpaRepository<LaLearnTechnology, Long> {

    Optional<LaLearnTechnology> findByLaTechId(Long techId);

    List<LaLearnTechnology> findByLaTechIdIn(List<Long> techIds);

    List<LaLearnTechnology> findByLaTechIdIn(List<Long> techIds, Sort sort);
    
    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where c.laTechId in (Select distinct cin.laTechGroup from LaLearnTechnology as cin)")
    Page<LaLearnTechnology> getAllRootCategory(Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where c.laTechId in (Select distinct cin.laTechCategory from LaLearnTechnology as cin where cin.laTechGroup = :rootCategoryId and cin.laTechId != :rootCategoryId) and c.laTechId != :rootCategoryId")
    Page<LaLearnTechnology> getAllParentCategory(@Param("rootCategoryId") String rootCategoryId, Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where c.laTechId in (Select distinct cin.laTechCategory from LaLearnTechnology as cin where cin.laTechGroup = :rootCategoryId and cin.laTechId != :rootCategoryId) and c.laTechId != :rootCategoryId and c.name like %:searchedValue%")
    Page<LaLearnTechnology> getAllParentCategoryForSearch(@Param("rootCategoryId") String rootCategoryId, Pageable pageable, @Param("searchedValue") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where (c.laTechCategory = :parentCategoryId or c.laTechGroup = :parentCategoryId) and c.laTechId != :parentCategoryId")
    Page<LaLearnTechnology> getAllChildCategory(@Param("parentCategoryId") String string, Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where (c.laTechCategory = :parentCategoryId or c.laTechGroup = :parentCategoryId) and c.laTechId != :parentCategoryId  and c.name like %:searchedValue%")
    Page<LaLearnTechnology> getAllChildCategoryForSearch(@Param("parentCategoryId") String string, Pageable pageable, @Param("searchedValue") String searchedValue);

    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where c.laTechId in (Select distinct cin.laTechCategory from LaLearnTechnology as cin where cin.laTechGroup = :rootCategoryId and cin.laTechId != :rootCategoryId) and c.laTechId != :rootCategoryId and c.name like %:searchedValue%")
    Page<LaLearnTechnology> getSearchResultsForOfficialCategory(@Param("searchedValue") String searchedValue, @Param("rootCategoryId") String rootCategoryId, Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnTechnology as c "
    		+ "where c.laTechId in (Select distinct cin.laTechCategory from LaLearnTechnology as cin where cin.laTechGroup = :rootCategoryId and cin.laTechId != :rootCategoryId) and c.laTechId != :rootCategoryId and c.name like %:searchedValue%")
    Page<LaLearnTechnology> getSearchResultsForSpecialCategory(@Param("searchedValue") String searchedValue, @Param("rootCategoryId") String rootCategoryId, Pageable pageable);
    
}
