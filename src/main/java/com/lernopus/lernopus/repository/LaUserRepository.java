package com.lernopus.lernopus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;

/**
 * Created by amernath v on 2019-09-04.
 */
@Repository
public interface LaUserRepository extends JpaRepository<LaLearnUser, Long> {
	Optional<LaLearnUser> findByLaUserName(String laUserName);

	Optional<LaLearnUser> findByLaMailId(String laMailId);
    
    Optional<LaLearnUser> findByLaPhoneNumber(Long laPhoneNumber);

    Boolean existsByLaUserName(String laUserName);

    Boolean existsByLaMailId(String laMailId);
    
    Boolean existsByLaPhoneNumber(Long laPhoneNumber);
    
    List<LaLearnUser> findByLaUserIdIn(List<Long> laUserIds);
    
    Optional<LaLearnUser> findByLaUserNameOrLaMailId(String laUserName, String laMailId);
    
    Optional<LaLearnUser> findByLaUserNameOrLaPhoneNumber(String laUserName, Long laPhoneNumber);
    
    Optional<LaLearnUser> findByLaUserNameOrLaMailIdOrLaPhoneNumber(String laUserName, String laMailId, Long laPhoneNumber);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId in (Select distinct cin.laCreatedUser from LaLearnCourse as cin where cin.laCreatedUser != :loggedInUserId)")
    Page<LaLearnUser> getAllAuthor(@Param("loggedInUserId") Long loggedInUserId, Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId in (Select distinct cin.laCreatedUser from LaLearnCourse as cin where cin.laCreatedUser != :loggedInUserId) and c.laUserFullName  like %:searchedValue%")
    Page<LaLearnUser> getAllAuthorForSearch(@Param("loggedInUserId") Long loggedInUserId, Pageable pageable, @Param("searchedValue") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId in (Select distinct cin.laCreatedUser from LaLearnCourse as cin where cin.laCreatedUser != :loggedInUserId and :learnTechnology member of cin.laTechTag)")
    Page<LaLearnUser> getAllAuthorForCategory(@Param("learnTechnology") LaLearnTechnology learnTechnology,@Param("loggedInUserId") Long loggedInUserId, Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId in (Select distinct cin.laCreatedUser from LaLearnCourse as cin where cin.laCreatedUser != :loggedInUserId and :learnTechnology member of cin.laTechTag)  and c.laUserFullName  like %:searchedValue%")
    Page<LaLearnUser> getAllAuthorForCategoryForSearch(@Param("learnTechnology") LaLearnTechnology learnTechnology,@Param("loggedInUserId") Long loggedInUserId, Pageable pageable, @Param("searchedValue") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId in (Select distinct cin.laCreatedUser from LaLearnCourse as cin where cin.laCreatedUser != :loggedInUserId) and c.laUserFullName  like %:searchedValue%")
    Page<LaLearnUser> getSearchResultsForAuthors(@Param("searchedValue") String searchedValue, @Param("loggedInUserId") Long loggedInUserId, Pageable pageable);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId  != :loggedInUserId and c.laUserFullName  like %:searchedValue%")
    Page<LaLearnUser> getSearchResultsForUsers(@Param("searchedValue") String searchedValue, @Param("loggedInUserId") Long loggedInUserId, Pageable pageable);
}
