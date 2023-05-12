package com.dev2win.iniciativas.faces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.InitiativeService;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.users.UserService;

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
    FacesContextWrapper facesContextWrapper;

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;

    private String description;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String userName;
    private List<Initiative> initiatives = new ArrayList<>();
    private List<Initiative> selectedInitiatives;
    private Initiative selectedInitiative;
    private static final String INITIATIVES_MENU_MESSAGES = "initiatives-menu:messages";
    private static final String INITIATIVES_MENU_INITIATIVES_LIST = "initiatives-menu:initiatives-list";

    /**
     * Empty contructor
     */
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

    public int saveInitiative(String userName) {
        int flag = -1;
        if (this.selectedInitiative.getUser() == null) {
            User userOwner = userService.getUserByMail(userName);
            this.selectedInitiative.setUser(userOwner);
            this.selectedInitiative.setDate(LocalDate.now());
            this.selectedInitiative.setState(State.OPEN.getValue());
            initiativeService.addInitiative(this.selectedInitiative);
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Added"));
            flag = 1;
        } else {
            initiativeService.updateInitiative(this.selectedInitiative);
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Updated"));
            flag = 0;
        }

        primeFacesWrapper.current().executeScript("PF('manageIdeaDialog').hide()");
        primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
        return flag;
    }

    public void deleteInitiative() {
        facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Removed"));
        primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
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
                primeFacesWrapper.current().executeScript("PF('deleteInitiativeDialog').show()");
            } else if (dialogType.equals("edit")) {
                primeFacesWrapper.current().executeScript("PF('manageIdeaDialog').show()");
            }
        } else {
            facesContextWrapper.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Is not your initiative", "Error"));
            primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
        }
    }

}
