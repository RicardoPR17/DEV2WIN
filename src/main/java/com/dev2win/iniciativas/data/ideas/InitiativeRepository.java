package com.dev2win.iniciativas.data.ideas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, Long> {
}
