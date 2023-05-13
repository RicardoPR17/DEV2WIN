package com.dev2win.iniciativas;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dev2win.iniciativas.data.users.*;

@SpringBootApplication
public class IniciativasApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(IniciativasApplication.class, args);
    }

    @Bean
    ServletRegistrationBean<FacesServlet> jsfServletRegistration(ServletContext servletContext) {
        // spring boot only works if this is set
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        // registration
        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);

        userService.getAllUsers().forEach(user -> userService.deleteUser(user.getUserId()));

        User user = new User("prueba", "contrasena", Role.ADMINISTRADOR, "desarrollo", Profile.ESTUDIANTE,
                "prueba@mail.escuelaing.edu.co");
        User user2 = new User("prueba2", "contrasena2", Role.PROPONENTE, "desarrollo", Profile.ESTUDIANTE,
                "prueba2@mail.escuelaing.edu.co");
        userService.addUser(user);
        userService.addUser(user2);
        return srb;
    }

}
