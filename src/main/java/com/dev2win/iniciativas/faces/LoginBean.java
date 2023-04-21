package com.dev2win.iniciativas.faces;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.usuarios.UsuarioService;

@Component
@ManagedBean(name = "loginBean")
@SessionScope
public class LoginBean {

    @Autowired
    UsuarioService usuarioService;
    
    private String nombreUsuario;
    private String contrasena;

    public LoginBean () {}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void login() {
        // Verficiar que la contrasena del usuario corresponde
        // Get de contrasena base de datos y comparar con el campo enviado
        boolean auntenticado = contrasena.equals(usuarioService.getUsuarioByNombre(nombreUsuario).getContrasena());
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
