package com.dev2win.iniciativas.data.ideas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, Long> {
    @Query("SELECT i FROM Initiative i WHERE i.keyword1=:keyword OR i.keyword2=:keyword OR i.keyword3=:keyword")
    List<Initiative> findByKeyword(@Param("keyword") String keyword);
}
