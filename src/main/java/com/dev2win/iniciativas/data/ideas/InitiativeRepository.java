package com.dev2win.iniciativas.data.ideas;

import com.dev2win.iniciativas.data.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, Long>{
    @Query("SELECT i FROM Initiative i WHERE i.keyword1=:keyword1")
    List<Initiative> findByKeyword1(@Param("keyword1") String keyword1);

    @Query("SELECT i FROM Initiative i WHERE i.keyword2=:keyword2")
    List<Initiative> findByKeyword2(@Param("keyword2") String keyword2);

    @Query("SELECT i FROM Initiative i WHERE i.keyword3=:keyword3")
    List<Initiative> findByKeyword3(@Param("keyword3") String keyword3);
}
