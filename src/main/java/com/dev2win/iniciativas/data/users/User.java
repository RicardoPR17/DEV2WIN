package com.dev2win.iniciativas.data.users;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserId;
    private String name;
    private String password;
    private String role;
    private String state;
    private String perfil;

    public User(String name, String password, Role role, String state, Profile perfil) {
        this.name = name;
        this.role = role.getValue();
        this.state = state;
        this.perfil = perfil.getValue();
        this.password = password;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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
        result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return Objects.equals(UserId, other.UserId) &&
               Objects.equals(name, other.name) &&
               Objects.equals(password, other.password) &&
               Objects.equals(role, other.role) &&
               Objects.equals(state, other.state) &&
               Objects.equals(perfil, other.perfil);
    }
    
    @Override
    public String toString() {
        return String.format("User [UserId=%s, name=%s, password=%s, role=%s, state=%s, perfil=%s]",
                             UserId, name, password, role, state, perfil);
    }

}
