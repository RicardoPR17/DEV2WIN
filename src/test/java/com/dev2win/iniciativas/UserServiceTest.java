package com.dev2win.iniciativas;

import static org.mockito.Mockito.when;

import com.dev2win.iniciativas.data.users.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock // Esto es un mock de la inyección de dependencias
    private UserRepository userRepository;

    @InjectMocks // Esta es la clase real
    private UserService userService;

    private User user;

    @BeforeAll
    void setup() {
        // this starts only the first time of we execute with class test
        MockitoAnnotations.initMocks(this);
        user = new User("Jorge", "Pass", Role.Administrador, "", Profile.Directivo, "jorge.useche@gmail.com");
        user.setUserId(1L);
        when(userRepository.save(user)).thenReturn(user);
    }

    @BeforeEach
    void initUseCase() {
        // Ponga aquí lo que quieres que se inicie cada vez que ejecute un test
    }

    @Test
    void savedUserIsSuccessfullyCreated() {
        User newUser = userService.addUser(user);
        assertNotNull(newUser.getUserId());
    }

}
