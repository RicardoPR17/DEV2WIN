package com.dev2win.iniciativas.faces;

import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Service;

@Service
public class PrimeFacesWrapper {
    public PrimeFaces current() {
        return PrimeFaces.current();
    }
}
