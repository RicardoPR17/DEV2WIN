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

        User user = new User("prueba", "1234", Role.ADMINISTRADOR, "desarrollo", Profile.ESTUDIANTE,
                "ricardo@dev2win.com");
        User user2 = new User("prueba2", "1234", Role.PROPONENTE, "desarrollo", Profile.ESTUDIANTE,
                "angie@dev2win.com");
        userService.addUser(user);
        userService.addUser(user2);
        return srb;
    }

}
