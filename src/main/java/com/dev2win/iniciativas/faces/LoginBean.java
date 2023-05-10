package com.dev2win.iniciativas.faces;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    FacesContextWrapper facesContextWrapper;

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;

    private String userName;
    private String password;
    private static final String LOGIN_FORM_MESSAGES = "login-form:messages";
    private static final String ERROR = "Error";
    private User newUser;

    /**
     * Empty contructor
     */
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
    }

    public Boolean createAccount() {
        Boolean flag = true;
        try {
            if (!isValidEmail(this.newUser.getMail())) {
                facesContextWrapper.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not valid email", ERROR));
                primeFacesWrapper.current().ajax().update(LOGIN_FORM_MESSAGES);
                flag = false;
                return false;
            }
    
            if (userService.getUserByMail(this.newUser.getMail()) != null) {
                facesContextWrapper.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account already exists", ERROR));
                primeFacesWrapper.current().ajax().update(LOGIN_FORM_MESSAGES);
                flag = false;
                return false;
            }
    
            this.newUser.setRole(Role.PROPONENTE.getValue());
            this.newUser.setState("desarrollo");
            userService.addUser(this.newUser);
    
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Account created successfully"));
            primeFacesWrapper.current().ajax().update(LOGIN_FORM_MESSAGES);
            primeFacesWrapper.current().executeScript("PF('createAccountDialog').hide()");
            
        } catch (Exception e) {
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "An error occurred while creating the account", ERROR));
            primeFacesWrapper.current().ajax().update(LOGIN_FORM_MESSAGES);
            e.printStackTrace();
        }finally{
            return flag;
        }
    }
    
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    
    public Boolean login() {
        // Verificar que se ingresó un nombre de usuario y una contraseña
        if (password == null || userName == null) {
            facesContextWrapper.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter your username and password", ERROR));
            return false;
        }
        // Buscar al usuario por correo electrónico
        User userToLogin = userService.getUserByMail(userName);
        // Si el usuario no existe o la contraseña es incorrecta, mostrar un mensaje de
        // error y salir temprano
        if (userToLogin == null || !password.equals(userToLogin.getPassword())) {
            facesContextWrapper.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "The username or password are incorrect", ERROR));
            primeFacesWrapper.current().ajax().update(LOGIN_FORM_MESSAGES);
            return false;
        }
        // Si el usuario está autenticado, redirigirlo a la página correspondiente
        try {
            ExternalContext ec = facesContextWrapper.getCurrentInstance().getExternalContext();
            String redirectPath = getRedirectPath(userToLogin);
            ec.redirect(ec.getRequestContextPath() + redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    private String getRedirectPath(User user) {
        if (user.getRole().equals("Administrador")) {
            return "../pages/welcomeAdmin.xhtml";
        } else {
            return "../pages/welcomeProponent.xhtml";
        }
    }
    
}
