package com.dev2win.iniciativas;

import java.util.Arrays;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dev2win.iniciativas.data.comments.CommentService;
import com.dev2win.iniciativas.data.ideas.*;
import com.dev2win.iniciativas.data.likes.UpvoteService;
import com.dev2win.iniciativas.data.topic.*;
import com.dev2win.iniciativas.data.users.*;

@SpringBootApplication
public class IniciativasApplication {

    @Autowired
    UserService userService;

    @Autowired
    InitiativeService iniciativeService;

    @Autowired
    CommentService commentService;

    @Autowired
    UpvoteService upvoteService;

    @Autowired
    TopicService topicService;

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

        upvoteService.deleteAll();
        commentService.deleteAll();
        iniciativeService.deleteAll();
        topicService.deleteAll();
        userService.deleteAll();

        User user = new User("Ricardo", "1234", Role.ADMINISTRADOR, "desarrollo", Profile.ESTUDIANTE,
                "ricardo@dev2win.com");
        User user2 = new User("Angie", "1234", Role.PROPONENTE, "desarrollo", Profile.ESTUDIANTE,
                "angie@dev2win.com");
        userService.addUser(user);
        userService.addUser(user2);

        return srb;
    }

}
