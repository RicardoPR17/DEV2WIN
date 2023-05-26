package com.dev2win.iniciativas.data.ideas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dev2win.iniciativas.data.comments.Comment;
import com.dev2win.iniciativas.data.likes.Upvote;
import com.dev2win.iniciativas.data.topic.Topic;
import com.dev2win.iniciativas.data.users.User;

@Entity
public class Initiative {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long initiativeId;
    private String description;
    @Column(name = "registration_date")
    private LocalDate date;
    private String state;
    private String numberLikes;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String area;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "initiative", cascade = { CascadeType.REMOVE })
    List<Upvote> upvotes = new ArrayList<>();

    @OneToMany(mappedBy = "initiative", cascade = { CascadeType.REMOVE })
    List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    public Initiative(String description, State state, String keyword1, String keyword2, String keyword3, User user,
            Area area) {
        this.description = description;
        this.state = state.getValue();
        this.area = area.getValue();
        this.date = LocalDate.now();
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.user = user;
        this.numberLikes = "0";
        this.topic = null;
    }

    public Initiative() {

    }

    public Long getInitiativeId() {
        return initiativeId;
    }

    public void setInitiativeId(Long initiativeId) {
        this.initiativeId = initiativeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(new Locale("es", "ES"));
        return date.format(formatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeywords() {
        return keyword1 + "; " + keyword2 + "; " + keyword3;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getNumberLikes() {
        return numberLikes;
    }

    public void setNumberLikes(String numberLikes) {
        this.numberLikes = numberLikes;
    }

    public List<Upvote> getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(List<Upvote> upvotes) {
        this.upvotes = upvotes;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Initiative [initiativeId = " + initiativeId + ", description = " + description + ", date = " + date
                + ", state = "
                + state + ", keyword1 = " + keyword1 + ", keyword2 = " + keyword2 + ", keyword3 = " + keyword3
                + ", user = "
                + user
                + "]";
    }

}
