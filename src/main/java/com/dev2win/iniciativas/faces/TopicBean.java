package com.dev2win.iniciativas.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
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

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;

    @Autowired
    FacesContextWrapper facesContextWrapper;

    private String topic;
    private Topic selectedTopic;
    private List<Topic> topics = new ArrayList<>();
    private List<Initiative> initiatives;
    private List<Initiative> selectedInitiatives;

    private static final String TOPIC_MENU_MESSAGES = "topic-menu:messages";
    private static final String TOPIC_MENU_TOPIC_LIST = "topic-menu:topic-list";

    /**
     * Empty constructor
     */
    public TopicBean() {
        // empty constructor
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Initiative> getSelectedInitiatives() {
        return selectedInitiatives;
    }

    public void setSelectedInitiatives(List<Initiative> selectedInitiatives) {
        this.selectedInitiatives = selectedInitiatives;
    }

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public void setInitiatives(List<Initiative> initiatives) {
        this.initiatives = initiatives;
    }

    public void newTopic() {
        this.selectedTopic = new Topic();
    }

    //Registra un nuevo topic en la aplicación.
    public void saveTopic() {
        topicService.addTopic(this.selectedTopic);
        this.selectedTopic = null;
        facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Created topic"));
        primeFacesWrapper.current().ajax().update(TOPIC_MENU_MESSAGES);
    }

    public void onDatabaseLoaded() {
        this.initiatives = initiativeService.getAllInitiatives();
        this.topics = topicService.getAllTopics();
    }

    //Remueve el topic seleccionado de las iniciativas que lo posean, y luego lo elimina.
    public void deleteTopic() {
        Topic topicToDelete = topicService.getTopicByTopicName(topic);
        List<Initiative> topicInitiatives = initiativeService.getInitiativesByTopic(topicToDelete.getTopicId());
        for (Initiative initiative : topicInitiatives) {
            initiative.setTopic(null);
            initiativeService.updateInitiative(initiative);
        }
        topicService.deleteTopic(topicToDelete.getTopicId());
        facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Deleted topic"));
        primeFacesWrapper.current().ajax().update(TOPIC_MENU_TOPIC_LIST, TOPIC_MENU_MESSAGES);
    }

    //Agrega una iniciativa al topic seleccionado.
    public void addInitiativeToTopic() {
        Topic topicToAdd = topicService.getTopicByTopicName(topic);
        try {
            for (Initiative initiative : selectedInitiatives) {
                initiative.setTopic(topicToAdd);
                initiativeService.updateInitiative(initiative);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Grouped by selected topic"));
            primeFacesWrapper.current().ajax().update(TOPIC_MENU_TOPIC_LIST, TOPIC_MENU_MESSAGES);
        }
    }

    //Devuelve un mensaje para mostrar por pantalla al usuario según si se ha seleccionado solo una iniciativa
    //o más de una.
    public String getUpdateButtonMessage() {
        String message = "Group";
        if (hasSelectedInitiatives()) {
            int size = this.selectedInitiatives.size();
            return size > 1 ? message + " " + size + " initiatives selected" : message + " 1 initiative selected";
        }
        return message;
    }

    //Devuelve un valor booleano indicando si se ha seleccionado al menos una iniciativa.
    public boolean hasSelectedInitiatives() {
        return this.selectedInitiatives != null && !this.selectedInitiatives.isEmpty();
    }
}
