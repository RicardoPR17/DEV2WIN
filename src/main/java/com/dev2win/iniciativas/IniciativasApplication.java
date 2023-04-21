package com.dev2win.iniciativas;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dev2win.iniciativas.data.usuarios.Usuario;
import com.dev2win.iniciativas.data.usuarios.UsuarioService;

@SpringBootApplication
public class IniciativasApplication {

    @Autowired
    UsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(IniciativasApplication.class, args);
    }

    @Bean
    ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
        //spring boot only works if this is set
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        //registration
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);

        //adduser
        usuarioService.addUsuario(new Usuario("prueba", "contrasena", "dev", "desarrollo", "estudiante"));
        return srb;
    }

}
