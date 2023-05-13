package com.dev2win.iniciativas.data.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

    //Trae los likes que tiene una iniciativa
    @Query("SELECT u FROM Upvote u WHERE u.initiative.initiativeId=:initiativeId")
    List<Upvote> findByInitiative(@Param("initiativeId") Long initiativeId);

    //Trae los likes de un usuario
    @Query("SELECT u FROM Upvote u WHERE u.user=:userId")
    List<Upvote> findByUser(@Param("userId") Long userId);

    //Trae el like de un usuario a una iniciativa si existe
    @Query("SELECT u FROM Upvote u WHERE u.initiative.initiativeId=:initiativeId AND u.user.userId=:userId")
    List<Upvote> findUpvote(@Param("initiativeId") Long initiativeId, @Param("userId") Long userId);


}
