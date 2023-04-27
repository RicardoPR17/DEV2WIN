package com.dev2win.iniciativas;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.InitiativeService;
import com.dev2win.iniciativas.data.users.*;

@SpringBootApplication
public class IniciativasApplication {

    @Autowired
    UserService userService;
    //InitiativeService initiativeService;

    public static void main(String[] args) {
        SpringApplication.run(IniciativasApplication.class, args);
    }

    
    @Bean
    public CommandLineRunner run() throws Exception {
        return (args) -> {
            System.out.println("Deleting users and initiatives on DB...\n");
            //initiativeService.getAllInitiatives().forEach(initiative -> initiativeService.deleteInitiative(initiative.getInitiativeId()));
            //userService.getAllUsers().forEach(user -> userService.deleteUser(user.getUserId()));

            // System.out.println(userService.getUserByProfile("Estudiante"));

            User user = new User("prueba", "contrasena", Role.Administrador, "desarrollo", Profile.Estudiante, "prueba@mail.escuelaing.edu.co");
            Initiative initiativeOne = new Initiative("description", "status", null, null, null, user);
            Initiative initiativeTwo = new Initiative("description", "revision", null, null, null, user);
            //Si ya se crearon varios usuarios iguales da error al registrar iniciativas.
            //userService.deleteAll();
            //initiativeService.deleteAll();
            user.getIdeas().add(initiativeOne);
            user.getIdeas().add(initiativeTwo);
            userService.addUser(user);
            //initiativeService.addInitiative(initiativeOne);
            //initiativeService.addInitiative(initiativeTwo);
        };
    }
     

    @Bean
    ServletRegistrationBean jsfServletRegistration(ServletContext servletContext) {
        // spring boot only works if this is set
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        // registration
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);

        return srb;
    }

}
