package com.dev2win.iniciativas.faces;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.users.Role;
import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.users.UserService;

@Component
@ManagedBean(name = "loginBean")
@SessionScope
public class LoginBean {

    @Autowired
    UserService userService;

    private String userName;
    private String password;

    private User newUser;

    public LoginBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public void createUserAccount() {
        this.newUser = new User();
        this.userName = "";
        this.password = "";
    }

    public void createAccount() {
        try {
            User userToCreate = userService.getUserByMail(this.newUser.getMail());
                    
            if (userToCreate == null) {
                this.newUser.setRole(Role.Proponente.getValue());
                this.newUser.setState("desarrollo");
                userService.addUser(this.newUser);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account Created Successfully"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Already Account Exist"));
            }
            PrimeFaces.current().executeScript("PF('createAccountDialog').hide()");
            PrimeFaces.current().ajax().update("login-form:messages");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void login() {
        // Verficiar que la password del usuario corresponde
        // Get de password base de datos y comparar con el campo enviado
        User user = userService.getUserByMail(userName);
        boolean auntenticado = password.equals(user.getPassword());
        if (auntenticado) {
            try {
                // enviar a pagina de bienvenida general
                // eso depende del rol (administrador, proponente...)
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                if (user.getRole().equals("Administrador")) {
                    ec.redirect(ec.getRequestContextPath() + "../pages/welcomeAdmin.xhtml");
                } else {
                    ec.redirect(ec.getRequestContextPath() + "../pages/newInitiative.xhtml");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The username or password are incorrect"));
    }
}
