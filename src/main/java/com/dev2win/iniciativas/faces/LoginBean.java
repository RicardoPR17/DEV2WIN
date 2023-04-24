package com.dev2win.iniciativas.faces;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.users.UserService;

@Component
@ManagedBean(name = "loginBean")
@SessionScope
public class LoginBean {

    @Autowired
    UserService userService;

    private String userName;
    private String password;

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

    public void login() {
        // Verficiar que la password del usuario corresponde
        // Get de password base de datos y comparar con el campo enviado
        boolean auntenticado = password.equals(userService.getUserByName(userName).getPassword());
        if (auntenticado) {
            try {
                // enviar a pagina de bienvenida general
                // eso depende del rol (administrador, proponente...)
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "../pages/welcome.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
