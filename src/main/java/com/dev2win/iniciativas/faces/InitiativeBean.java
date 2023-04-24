package com.dev2win.iniciativas.faces;

import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.InitiativeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ManagedBean(name = "initiativeBean")
@SessionScope
public class InitiativeBean {
    @Autowired
    InitiativeService initiativeService;
    private String description;
    private String keyword1;
    private String keyword2;
    private String keyword3;

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

    public void add(){
        try {
            //Creamos la iniciativa, le seteamos la fecha actual y la agregamos a la BD mediante el servicio.
            Initiative newInitiative = new Initiative(description, "created");
            newInitiative.setDate(LocalDate.now());
            initiativeService.addInitiative(newInitiative);
            //Redirigimos a una página de éxito
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "../pages/initiativeSuccess.xhtml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
