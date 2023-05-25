package com.dev2win.iniciativas.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.ideas.*;
import com.dev2win.iniciativas.data.topic.Topic;
import com.dev2win.iniciativas.data.topic.TopicService;

@Component
@ManagedBean(name = "topicBean")
@SessionScope
public class TopicBean {
    @Autowired
    TopicService topicService;

    @Autowired
    InitiativeService initiativeService;
    private String topic = "";
    private List<Initiative> selectedInitiatives = new ArrayList<>();

    /**
     * Empty constructor
     */
    public TopicBean() {
        // empty constructor
    }

    public void createTopic(String name) {
        topicService.addTopic(new Topic(name));
    }

    public void deleteTopic(String topic) {
        Topic topicToDelete = topicService.getTopicByTopicName(topic);
        List<Initiative> topicInitiatives = topicToDelete.getInitiatives();

        for (Initiative i : topicInitiatives) {
            i.setTopic(null);
        }

        topicService.deleteTopic(topicToDelete.getTopicId());
    }

    public void addInitiativeToTopic() {
        try {
            for (Initiative i : selectedInitiatives) {
                i.setTopic(topicService.getTopicByTopicName(topic));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasSelectedInitiatives() {
        return this.selectedInitiatives != null && !this.selectedInitiatives.isEmpty();
    }
}
