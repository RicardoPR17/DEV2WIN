package com.dev2win.iniciativas.data.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dev2win.iniciativas.data.comments.Comment;
import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.likes.Upvote;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String password;
    private String role;
    private String state;
    private String profile;
    private String mail;
    @OneToMany(mappedBy = "user")
    List<Initiative> ideas = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    List<Upvote> upvotes = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    public User(String name, String password, Role role, String state, Profile profile, String mail) {
        this.name = name;
        this.role = role.getValue();
        this.state = state;
        this.profile = profile.getValue();
        this.password = password;
        this.mail = mail;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Initiative> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Initiative> ideas) {
        this.ideas = ideas;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((profile == null) ? 0 : profile.hashCode());
        result = prime * result + ((mail == null) ? 0 : mail.hashCode());
        result = prime * result + ((ideas == null) ? 0 : ideas.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User [UserId = " + userId + ", name = " + name + ", password = " + password + ", role = " + role
                + ", state = "
                + state + ", profile = " + profile + "mail = " + mail + "]";
    }

}
