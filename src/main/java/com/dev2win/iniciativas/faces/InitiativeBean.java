package com.dev2win.iniciativas.faces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.InitiativeService;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.likes.Upvote;
import com.dev2win.iniciativas.data.likes.UpvoteService;
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
    @Autowired
    UpvoteService upvoteService;
    private String description;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String userName;
    private List<Initiative> initiatives = new ArrayList<>();;
    private List<Initiative> selectedInitiatives;
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

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public void setInitiatives(List<Initiative> initiatives) {
        this.initiatives = initiatives;
    }

    public List<Initiative> getSelectedInitiatives() {
        return selectedInitiatives;
    }

    public void setSelectedInitiatives(List<Initiative> selectedInitiatives) {
        this.selectedInitiatives = selectedInitiatives;
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

    public void onDatabaseLoaded() {
        this.initiatives = initiativeService.getAllInitiatives();
    }

    public void newInitiative() {
        this.selectedInitiative = new Initiative();
    }

    public void saveInitiative(String userName) {
        if (this.selectedInitiative.getUser() == null) {
            User userOwner = userService.getUserByMail(userName);
            this.selectedInitiative.setUser(userOwner);
            this.selectedInitiative.setDate(LocalDate.now());
            this.selectedInitiative.setState(State.Open.getValue());
            this.selectedInitiative.setNumberLikes("0");
            initiativeService.addInitiative(this.selectedInitiative);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Added"));
        } else {
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

    public String getUpdateButtonMessage() {
        if (hasSelectedInitiatives()) {
            int size = this.selectedInitiatives.size();
            return size > 1 ? size + " initiatives selected" : "1 initiative selected";
        }

        return "Update";
    }

    public boolean hasSelectedInitiatives() {
        return this.selectedInitiatives != null && !this.selectedInitiatives.isEmpty();
    }

    public void isYourInitiative(String userName, String dialogType) {
        if (this.selectedInitiative.getUser().getMail().equals(userName)) {
            if (dialogType.equals("delete")) {
                PrimeFaces.current().executeScript("PF('deleteInitiativeDialog').show()");
            } else if (dialogType.equals("edit")) {
                PrimeFaces.current().executeScript("PF('manageIdeaDialog').show()");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Is not your initiative", "Error"));
            PrimeFaces.current().ajax().update("initiatives-menu:messages", "initiatives-menu:initiatives-list");
        }
    }

    public void upvoteInitiative() {
        if (this.selectedInitiative != null) {
            PrimeFaces.current().executeScript("PF('upvoteInitiativeDialog').show()");
            PrimeFaces.current().ajax().update("dialogs:upvote-content");
        }
    }

    public boolean isUpvoted(String userName) {
        User user = userService.getUserByMail(userName);
        if (this.selectedInitiative != null) {
            if (!upvoteService.getUpvote(this.selectedInitiative, user).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public String upvoteMessage(String userName) {
        String message = "Do you want to upvote this initiative?";
        if (isUpvoted(userName)) {
            message = "Do you want to remove your upvote?";
        }
        return message;
    }

    public void changeVote(String userName) {
        User user = userService.getUserByMail(userName);
        if (this.selectedInitiative != null) {
            if (isUpvoted(userName)) { 
                Upvote upvote = upvoteService.getUpvote(this.selectedInitiative, user).get(0);
                upvoteService.delete(upvote);
            } else {
                Upvote newUpvote = new Upvote(this.selectedInitiative, user);
                upvoteService.addUpvote(newUpvote);
            }
        }
        String counts = Integer.toString(upvoteService.getInitiativeUpvoteCount(this.selectedInitiative));
        this.selectedInitiative.setNumberLikes(counts);
        initiativeService.updateInitiative(this.selectedInitiative);
        PrimeFaces.current().ajax().update("initiatives-menu:initiatives-list");
    }

}
