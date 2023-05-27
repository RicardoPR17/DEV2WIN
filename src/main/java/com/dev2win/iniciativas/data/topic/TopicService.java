package com.dev2win.iniciativas.data.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic getTopic(Long topicId) {
        return topicRepository.findById(topicId).get();
    }

    public Topic getTopicByTopicName(String topicName) {
        return topicRepository.findByTopicName(topicName).get();
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic updateTopic(Topic topic) {
        if (topicRepository.existsById(topic.getTopicId())) {
            return topicRepository.save(topic);
        }
        return null;
    }

    public void deleteTopic(Long topicId) {
        topicRepository.deleteById(topicId);
    }

    public void deleteAll() {
        topicRepository.deleteAll();
    }
}
