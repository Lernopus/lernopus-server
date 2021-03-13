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
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.model.QLaLearnUser;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

/**
 * Created by amernath v on 2019-09-04.
 */
@Repository
public interface LaUserApiRepository extends JpaRepository<LaLearnUser, Long>, QuerydslPredicateExecutor<LaLearnUser>, QuerydslBinderCustomizer<QLaLearnUser> {
	
    @Query("select c from LaLearnUser as c")
    Page<LaLearnUser> getAllUsers(Pageable pageable);
    
    @Query(value="select c from LaLearnUser as c "
    		+ "where c.:filterKey = :filterValue", nativeQuery = true)
    Page<LaLearnUser> getAllUsersWithConditions(Pageable pageable,@Param("filterKey") String filterKey, @Param("filterValue") String filterValue);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserName like %:userNameOrIdOrMail% or "
    		+ "c.laMailId like %:userNameOrIdOrMail% or c.laPhoneNumber like %:userNameOrIdOrMail%")
    Optional<LaLearnUser> searchUser(@Param("userNameOrIdOrMail") String searchedValue);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where c.laUserId = :userId")
    Optional<LaLearnUser> getUser(@Param("userId") Long userId);
    
    @Query("select c  "
    		+ "from LaLearnUser as c "
    		+ "where :filterKey = :filterValue")
    Optional<LaLearnUser> filterLaLearnUser(@Param("filterKey") String filterKey, @Param("filterValue") String filterValue);
    
    @Override
    default public void customize(
      QuerydslBindings bindings, QLaLearnUser root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        //bindings.excluding(root.email);
      }
}
