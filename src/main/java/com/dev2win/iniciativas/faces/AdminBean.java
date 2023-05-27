package com.dev2win.iniciativas.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.users.Role;
import com.dev2win.iniciativas.data.users.UserService;

@Component
@ManagedBean(name = "adminBean")
@SessionScope
public class AdminBean {
    @Autowired
    UserService userService;

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;

    @Autowired
    FacesContextWrapper facesContextWrapper;

    private String newRole = "";
    private List<User> users = new ArrayList<>();
    private List<User> selectedUsers = new ArrayList<>();
    private static final String USER_MOD_FORM_USER_LIST = "user-mod-form:users-list";
    private static final String USER_MOD_FORM_MESSAGES = "user-mod-form:messages";
    private static final String ROLE_LABEL = ":role-label";
    private static final String ERROR = "Error";

    /**
     * Empty contructor
     */
    public AdminBean() {
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }

    //Toma el rol de usuario seleccionado y lo setea como el rol de cada usuario en la lista de usuarios seleccionados.
    //Verifica que haya un rol seleccionado y retorna un valor booleano según si se había indicado un rol o no.
    public Boolean modifyUserRole() {
        if (this.newRole == null || this.newRole.isEmpty()) {
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Please select a role", ERROR));
            primeFacesWrapper.current().ajax().update(USER_MOD_FORM_MESSAGES);
            return false;
        }
        try {
            for (User user : selectedUsers) {
                user.setRole(Role.findByValue(newRole).getValue());
                userService.updateUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Users Updated"));
            primeFacesWrapper.current().ajax().update(USER_MOD_FORM_USER_LIST, USER_MOD_FORM_MESSAGES, ROLE_LABEL);
        }
        return true;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public void onDatabaseLoaded() {
        this.users = userService.getAllUsers();
    }

    //Devuelve un mensaje para mostrar por pantalla según si se han seleccionado múltiples usuarios o uno solo
    public String getUpdateButtonMessage() {
        String message = "Update";
        if (hasSelectedUsers()) {
            int size = this.selectedUsers.size();
            return size > 1 ? message + " " + size + " users selected" : message + " 1 user selected";
        }
        return message;
    }

    //Devuelve un valor booleano que indica si se ha seleccionado al menos un usuario o no.
    public boolean hasSelectedUsers() {
        return this.selectedUsers != null && !this.selectedUsers.isEmpty();
    }

}
