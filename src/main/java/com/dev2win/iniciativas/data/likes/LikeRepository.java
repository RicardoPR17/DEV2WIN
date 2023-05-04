package com.dev2win.iniciativas.data.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.initiative_id=:initiativeId")
    List<Like> findByInitiative(@Param("initiative_id") Long initiativeId);

    @Query("SELECT l FROM Like l WHERE l.user_id=:userId")
    List<Like> findByUser(@Param("user_id") Long userId);

}
