package com.dev2win.iniciativas.data.topic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dev2win.iniciativas.data.ideas.Initiative;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicId;
    private String topicName;
    @OneToMany(mappedBy = "topic")
    List<Initiative> initiatives = new ArrayList<>();

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public Topic() {
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public void setInitiatives(List<Initiative> initiatives) {
        this.initiatives = initiatives;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
        result = prime * result + ((topicName == null) ? 0 : topicName.hashCode());
        result = prime * result + ((initiatives == null) ? 0 : initiatives.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topic other = (Topic) obj;
        if (topicId == null) {
            if (other.topicId != null)
                return false;
        } else if (!topicId.equals(other.topicId))
            return false;
        if (topicName == null) {
            if (other.topicName != null)
                return false;
        } else if (!topicName.equals(other.topicName))
            return false;
        if (initiatives == null) {
            if (other.initiatives != null)
                return false;
        } else if (!initiatives.equals(other.initiatives))
            return false;
        return true;
    }
}
