package com.dev2win.iniciativas.data.ideas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, Long> {

    @Query("SELECT i FROM Initiative i WHERE i.user.userId=:userId")
    List<Initiative> findByUser(@Param("userId") Long userId);

    // Count initiatives with a specific state
    @Query("SELECT COUNT(i) FROM Initiative i WHERE i.state=:requiredState")
    Long countByState(@Param("requiredState") String requiredState);

    // Count initiatives with a specific area
    @Query("SELECT COUNT(i) FROM Initiative i WHERE i.area=:area")
    Long countByArea(@Param("area") String area);

    @Query("SELECT i FROM Initiative i WHERE i.topic.topicId=:topicId")
    List<Initiative> findByTopicId(@Param("topicId") Long topicId);
}
