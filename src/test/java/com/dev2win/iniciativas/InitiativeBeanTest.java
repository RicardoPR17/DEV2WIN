package com.dev2win.iniciativas;

import com.dev2win.iniciativas.data.users.*;
import com.dev2win.iniciativas.data.ideas.*;
import com.dev2win.iniciativas.faces.FacesContextWrapper;
import com.dev2win.iniciativas.faces.InitiativeBean;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InitiativeBeanTest {
    @Mock
    private FacesContextWrapper facesContextWrapper;

    @Mock
    private PrimeFacesWrapper primeFacesWrapper;

    @Mock // Esta es la clase real
    private InitiativeService initiativeService;

    @Mock // Esta es la clase real
    private UserService userService;

    @InjectMocks // Esta es la clase real
    private InitiativeBean initiativeBean;

    private User user;
    private Initiative selectedInitiative;
    private List<Initiative> initiatives = new ArrayList<Initiative>();
    private String userEmail;

    @BeforeAll
    void setup() {
        // this starts only the first time of we execute with class test
        MockitoAnnotations.initMocks(this);
        user = new User("Jorge", "Pass", Role.ADMINISTRADOR, "", Profile.DIRECTIVO, "juuseche@gmail.com");
        user.setUserId(10L);
        selectedInitiative = new Initiative("Test Initiative", State.APROVED, "keyword1", "keyword2", "keyword3", null, Area.ENVIRONMENT);
        selectedInitiative.setInitiativeId(11L);
        initiatives.add(selectedInitiative);
        userEmail = user.getMail();

        when(initiativeService.getAllInitiatives()).thenReturn(initiatives);
        // when(selectedInitiative.getUser()).thenReturn(user);
        when(userService.getUserByMail(userEmail)).thenReturn(user);
        //when(selectedInitiative.getUser().getMail()).thenReturn(user.getMail());

        Mocks mocks = new Mocks();
        when(facesContextWrapper.getCurrentInstance()).thenReturn(mocks.facesContextMock);
        when(primeFacesWrapper.current()).thenReturn(mocks.primeFaces);
    }

    @BeforeEach
    void initUseCase() {
        userService.deleteAll();
    }


    @Test
    void shouldSaveInitiative(){
        initiativeBean.setSelectedInitiative(selectedInitiative);
        int result = initiativeBean.saveInitiative(userEmail);
        assertEquals(1, result);
    }

    @Test
    void shouldUpdateInitiative(){
        initiativeBean.setSelectedInitiative(selectedInitiative);
        selectedInitiative.setUser(user);
        int result = initiativeBean.saveInitiative(userEmail);
        assertEquals(0, result);
    }

    @Test
    void shouldDetectSelectedInitiative(){
        initiativeBean.setSelectedInitiative(selectedInitiative);
        assertEquals(selectedInitiative, initiativeBean.getSelectedInitiative());
    }

    @Test
    void shouldDetectAllInitiatives(){
        initiativeBean.setSelectedInitiatives(initiatives);
        assertTrue(initiativeBean.hasSelectedInitiatives());
    }

    @Test
    void shouldGetAndSetInitiativeDescription(){
        String sample = "Daniel PO";
        initiativeBean.setDescription(sample);
        assertEquals(sample, initiativeBean.getDescription());
    }

    @Test
    void shouldGetAndSetUserName(){
        String sample = "Jessica Scrum team";
        initiativeBean.setUserName(sample);
        assertEquals(sample, initiativeBean.getUserName());
    }

    @Test
    void shouldGetAndSetKeywords(){
        String sample1 = "Ricardo Scrum team";
        String sample2 = "Nicolas Scrum team";
        String sample3 = "Angie Scrum team";
        initiativeBean.setKeyword1(sample1);
        initiativeBean.setKeyword2(sample2);
        initiativeBean.setKeyword3(sample3);
        assertEquals(sample1, initiativeBean.getKeyword1());
        assertEquals(sample2, initiativeBean.getKeyword2());
        assertEquals(sample3, initiativeBean.getKeyword3());
    }


}
