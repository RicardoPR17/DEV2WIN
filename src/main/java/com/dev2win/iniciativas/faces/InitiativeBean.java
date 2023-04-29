package com.dev2win.iniciativas.faces;

import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.InitiativeService;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.users.UserService;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ManagedBean(name = "initiativeBean")
@SessionScope
public class InitiativeBean {
    @Autowired
    InitiativeService initiativeService;
    @Autowired
    UserService userService;
    private String description;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String userName;

    private Initiative selectedInitiative;

    public InitiativeBean() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Initiative> getAll() {
        return initiativeService.getAllInitiatives();
    }

    public Initiative getSelectedInitiative() {
        return selectedInitiative;
    }

    public void setSelectedInitiative(Initiative selectedInitiative) {
        this.selectedInitiative = selectedInitiative;
    }

    public void newInitiative() {
        this.selectedInitiative = new Initiative();
    }

    public void saveInitiative(String userName) {
        if (this.selectedInitiative.getUser() == null) {
            User userOwner = userService.getUserByMail(userName);
            this.selectedInitiative.setUser(userOwner);
            this.selectedInitiative.setDate(LocalDate.now());
            this.selectedInitiative.setState(StateInitiative.Created);
            initiativeService.addInitiative(this.selectedInitiative);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Added"));
        }
        else {
            initiativeService.updateInitiative(this.selectedInitiative);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageIdeaDialog').hide()");
        PrimeFaces.current().ajax().update("initiatives-menu:messages", "initiatives-menu:initiatives-list");
    }


    public void deleteInitiative() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Removed"));
        PrimeFaces.current().ajax().update("initiatives-menu:messages", "initiatives-menu:initiatives-list");
        initiativeService.deleteInitiative(this.selectedInitiative.getInitiativeId());
    }

}
