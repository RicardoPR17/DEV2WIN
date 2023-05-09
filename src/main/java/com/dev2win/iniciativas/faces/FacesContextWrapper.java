package com.dev2win.iniciativas.faces;

import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;

@Service
public class FacesContextWrapper {

    public FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }

}
