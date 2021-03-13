package com.lernopus.lernopus.repository;

import com.lernopus.lernopus.model.LaLearnUserFollowers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaUserFollowersRepository extends JpaRepository<LaLearnUserFollowers, String> {

}
