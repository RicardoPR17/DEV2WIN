package com.dev2win.iniciativas;

import com.dev2win.iniciativas.data.users.*;
import com.dev2win.iniciativas.faces.FacesContextWrapper;
import com.dev2win.iniciativas.faces.LoginBean;
import com.dev2win.iniciativas.faces.PrimeFacesWrapper;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginBeanTest {
    @Mock
    private FacesContextWrapper facesContextWrapper;

    @Mock
    private PrimeFacesWrapper primeFacesWrapper;

    @Mock // Esta es la clase real
    private UserService userService;

    @InjectMocks // Esta es la clase real
    private LoginBean loginBean;

    private User user;
    private User user2;

    @BeforeAll
    void setup() {
        // this starts only the first time of we execute with class test
        MockitoAnnotations.initMocks(this);
        user = new User("Jorge", "Pass", Role.ADMINISTRADOR, "", Profile.DIRECTIVO, "juuseche@gmail.com");
        user.setUserId(10L);
        user2 = user;
        when(userService.getUserByMail("juusechec@gmail.com")).thenReturn(user);
        when(userService.getUserByMail("juusechec@gmail.com")).thenReturn(user2);
        Mocks mocks = new Mocks();
        when(facesContextWrapper.getCurrentInstance()).thenReturn(mocks.facesContextMock);
        when(primeFacesWrapper.current()).thenReturn(mocks.primeFaces);
    }

    @BeforeEach
    void initUseCase() {
        // Ponga aquí lo que quieres que se inicie cada vez que ejecute un test
    }

    @Test
    void checkIfLoginIsSuccess() {
        loginBean.setUserName("juusechec@gmail.com");
        loginBean.setPassword("Pass");
        Boolean loginResult = loginBean.login();
        assertTrue(loginResult);
    }

    @Test
    void checkIfLoginIsFailed() {
        loginBean.setUserName("noexite!!@gmail.com");
        loginBean.setPassword("Pass");
        Boolean loginResult = loginBean.login();
        assertFalse(loginResult);
    }

    @Test
    void shouldPassCorrectEmail(){
        String okEmail = "prueba@mail.escuelaing.edu.co";
        Boolean validate = loginBean.isValidEmail(okEmail);
        assertTrue(validate);
    }

    @Test
    void shouldNotPassIncorrectEmail(){
        String nOkEmail = "prueba mail escuelaing edu co";
        Boolean validate = loginBean.isValidEmail(nOkEmail);
        assertFalse(validate);
    }

    @Test
    void shouldAllowRegister() {
        loginBean.setNewUser(user);
        Boolean validate = loginBean.createAccount();
        assertTrue(validate);
    }

    // @Test
    // void shouldNotAllowDoubleRegister() {
    //     loginBean.setNewUser(user);
    //     loginBean.createAccount();
    //     loginBean.setNewUser(user2);
    //     Boolean validate = loginBean.createAccount();
    //     assertFalse(validate);
    // }

    // @Test
    // void shouldFailWhenNoUserInstanceExistsWhileCreatingAccount(){
    //     loginBean.setNewUser(null);
    //     loginBean.createAccount();
    //     assertThrows();
    // }

}
