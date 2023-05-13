package com.dev2win.iniciativas.faces;

import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;
import org.springframework.stereotype.Service;

@Service
public class PrimeFacesWrapper {
    public PrimeFaces current() {
        return PrimeFaces.current();
    }

    public PrimeRequestContext getRequestContext(){
        return PrimeRequestContext.getCurrentInstance(null);
    }
}
