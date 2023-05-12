package com.dev2win.iniciativas.data.comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//  List<Comment> findByInitiative(Long initiative_id);
  @Query("SELECT c FROM Comment c WHERE c.initiative.initiativeId=:initiativeId")
  List<Comment> findByInitiative(@Param("initiativeId") Long initiativeId);

}
