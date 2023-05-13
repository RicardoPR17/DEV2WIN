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
import static org.mockito.Mockito.when;

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
    private String userEmail;

    @BeforeAll
    void setup() {
        // this starts only the first time of we execute with class test
        MockitoAnnotations.initMocks(this);
        user = new User("Jorge", "Pass", Role.ADMINISTRADOR, "", Profile.DIRECTIVO, "juuseche@gmail.com");
        user.setUserId(10L);
        userEmail = user.getMail();
        when(userService.getUserByMail(userEmail)).thenReturn(user);
        Mocks mocks = new Mocks();
        when(facesContextWrapper.getCurrentInstance()).thenReturn(mocks.facesContextMock);
        when(primeFacesWrapper.current()).thenReturn(mocks.primeFaces);
    }

    @BeforeEach
    void initUseCase() {
        userService.deleteAll();
    }

    // @Test
    // void checkIfLoginIsSuccess() {
    // loginBean.setUserName("juusechec@gmail.com");
    // loginBean.setPassword("Pass");
    // Boolean loginResult = loginBean.login();
    // assertTrue(loginResult);
    // }

    @Test
    void checkIfLoginIsFailed() {
        loginBean.setUserName("noexite!!@gmail.com");
        loginBean.setPassword("Pass");
        Boolean loginResult = loginBean.login();
        assertFalse(loginResult);
    }

    @Test
    void shouldPassCorrectEmail() {
        String okEmail = "prueba@mail.escuelaing.edu.co";
        Boolean validate = loginBean.isValidEmail(okEmail);
        assertTrue(validate);
    }

    @Test
    void shouldNotPassIncorrectEmail() {
        String nOkEmail = "prueba mail escuelaing edu co";
        Boolean validate = loginBean.isValidEmail(nOkEmail);
        assertFalse(validate);
    }

    // @Test
    // void shouldAllowRegister() {
    // loginBean.setNewUser(user);
    // Boolean validate = loginBean.createAccount();
    // assertTrue(validate);
    // }

    @Test
    void shouldNotAllowDoubleRegister() {
        loginBean.setNewUser(user);
        loginBean.createAccount();
        Boolean validate = loginBean.createAccount();
        assertFalse(validate);
    }

    @Test
    void shouldSetAndGetUserName() {
        String test = "Rick";
        loginBean.setUserName(test);
        assertEquals(test, loginBean.getUserName());
    }

    @Test
    void shouldSetAndGetUserPassword() {
        String test = "angie123";
        loginBean.setPassword(test);
        assertEquals(test, loginBean.getPassword());
    }

    @Test
    void shouldSetAndGetUserInstance() {
        loginBean.setNewUser(user);
        assertEquals(user, loginBean.getNewUser());
    }

}
