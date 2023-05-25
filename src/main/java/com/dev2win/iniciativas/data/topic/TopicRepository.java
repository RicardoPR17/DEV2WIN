package com.dev2win.iniciativas.data.topic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTopicName(String topicName);
}
