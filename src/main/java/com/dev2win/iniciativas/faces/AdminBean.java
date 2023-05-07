package com.dev2win.iniciativas.faces;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.users.Role;
import com.dev2win.iniciativas.data.users.UserService;

@Component
@ManagedBean
@SessionScope
public class AdminBean {
    @Autowired
    UserService userService;

    User userToMod;
    String newRole;

    /**
     * Empty contructor
     */
    public AdminBean() {
    }

    public User getUserToMod() {
        return userToMod;
    }

    public void setUserToMod(User userToMod) {
        this.userToMod = userToMod;
    }

    public String getnewRole() {
        return newRole;
    }

    public void setnewRole(String newRole) {
        this.newRole = newRole;
    }

    public void searchUser(String userName) {
        setUserToMod(userService.getUserByName(userName));
    }

    public String getUserToModName() {
        if (userToMod != null) {
            return userToMod.getName();
        }
        return "";
    }

    public String getUserToModRole() {
        if (userToMod != null) {
            return userToMod.getRole();
        }
        return "";
    }

    public String getUserToModState() {
        if (userToMod != null) {
            return userToMod.getState();
        }
        return "";
    }

    public String getUserToModProfile() {
        if (userToMod != null) {
            return userToMod.getProfile();
        }
        return "";
    }

    public void modifyUserRole(String userName) {
        try {
            // Get the user given his name and setting his new role
            searchUser(userName);
            userToMod.setRole(Role.findByValue(newRole).getValue());
            userService.updateUsuario(userToMod);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clear();
        }
    }

    public void clear() {
        setUserToMod(null);
        setnewRole(null);
    }
}
