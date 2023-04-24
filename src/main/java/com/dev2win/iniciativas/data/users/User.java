package com.dev2win.iniciativas.data.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.dev2win.iniciativas.data.ideas.Initiative;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserId;
    private String name;
    private String password;
    private String role;
    private String state;
    private String profile;
    private String mail;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    List <Initiative> ideas = new ArrayList<>();
    
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
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
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

    public String getprofile() {
        return profile;
    }

    public void setprofile(String profile) {
        this.profile = profile;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }
    
    
    public List<Initiative> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Initiative> ideas) {
        this.ideas = ideas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((UserId == null) ? 0 : UserId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((profile == null) ? 0 : profile.hashCode());
        result = prime * result + ((mail == null) ? 0 : mail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        return Objects.equals(UserId, other.UserId) &&
                Objects.equals(name, other.name) &&
                Objects.equals(password, other.password) &&
                Objects.equals(role, other.role) &&
                Objects.equals(state, other.state) &&
                Objects.equals(profile, other.profile) &&
                Objects.equals(mail, other.mail);

    }

    @Override
    public String toString() {
        return "User [UserId = " + UserId + ", name = " + name + ", password = " + password + ", role = " + role
                + ", state = "
                + state + ", profile = " + profile + "mail = " + mail + "]";
    }

}
