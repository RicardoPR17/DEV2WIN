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
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.users.*;

@SpringBootApplication
public class IniciativasApplication {

        @Autowired
        UserService userService;
        @Autowired
        InitiativeService initiativeService;

        public static void main(String[] args) {
                SpringApplication.run(IniciativasApplication.class, args);
        }

        @Bean
        public CommandLineRunner run() throws Exception {
                return (args) -> {
                        System.out.println("Deleting users and initiatives on DB...\n");
                        initiativeService.getAllInitiatives().forEach(
                                        initiative -> initiativeService.deleteInitiative(initiative.getInitiativeId()));
                        userService.getAllUsers().forEach(user -> userService.deleteUser(user.getUserId()));

                        User user = new User("prueba", "contrasena", Role.Administrador, "desarrollo",
                                        Profile.Estudiante, "prueba@mail.escuelaing.edu.co");
                        User user2 = new User("Angie", "AngieM", Role.Proponente, "Melo",
                                        Profile.Estudiante, "angie@mail.escuelaing.edu.co");
                        userService.addUser(user);
                        userService.addUser(user2);
                        initiativeService
                                        .addInitiative(new Initiative("Prueba1", State.Open, "Prueba", "Ciclos",
                                                        "Proyecto", user));
                        initiativeService.addInitiative(
                                        new Initiative("Prueba2", State.Revision, "Proyecto", "Estandar", "Idea",
                                                        user));
                        initiativeService.addInitiative(
                                        new Initiative("Prueba3", State.Revision, "Ayuda", "Analisis", "Colaborar",
                                                        user));

                        // System.out.println(userService.getUserByProfile("Estudiante"));
                        // System.out.println(initiativeService.getByKeyword("Proyecto"));
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
