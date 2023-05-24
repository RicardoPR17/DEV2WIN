package com.dev2win.iniciativas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import com.dev2win.iniciativas.data.comments.Comment;
import com.dev2win.iniciativas.data.ideas.*;
import com.dev2win.iniciativas.data.users.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InitiativeTest {
    private Initiative initiative;
    private User user;
    private Comment comment1;
    private Comment comment2;

    @BeforeAll
    void setup() {
        user = new User("Jorge", "Pass", Role.ADMINISTRADOR, "", Profile.DIRECTIVO, "juuseche@gmail.com");
        initiative = new Initiative("Test Initiative", State.APROVED, "keyword1", "keyword2", "keyword3", null, Area.ENVIRONMENT);
        comment1 = new Comment("Comentario 1", user, initiative);
        comment2 = new Comment("Comentario 2", user, initiative);
    }

    @Test
    void shouldSetAndGetInitiativeId(){
        Long expected = 15L;
        initiative.setInitiativeId(expected);
        Long ans = initiative.getInitiativeId();
        assertEquals(expected, ans);
    }

    @Test
    void shouldSetAndGetDescription(){
        String expected = "Una descripcion";
        initiative.setDescription(expected);
        String ans = initiative.getDescription();
        assertEquals(expected, ans);
    }

    @Test
    void shouldSetAndGetDateAndDateFormat(){
        LocalDate expected1 = LocalDate.of(2023, 5, 24);
        String expected2 = "miércoles, 24 de mayo de 2023";
        initiative.setDate(expected1);
        LocalDate ans1 = initiative.getDate();
        String ans2 = initiative.getDateText();
        assertEquals(expected1, ans1);
        assertEquals(expected2, ans2);
    }

    @Test
    void shouldSetAndGetState(){
        String expected = "Review";
        initiative.setState(expected);
        String ans = initiative.getState();
        assertEquals(expected, ans);
    }

    @Test
    void shouldSetAndGetKeywords(){
        String k1 = "Keyword 1";
        String k2 = "Keyword 2";
        String k3 = "Keyword 3";
        String expected = "Keyword 1; Keyword 2; Keyword 3";
        initiative.setKeyword1(k1);
        initiative.setKeyword2(k2);
        initiative.setKeyword3(k3);
        String ans = initiative.getKeywords();
        assertEquals(expected, ans);
    }

    @Test
    void shouldSetAndGetArea(){
        String expected = "Healthy life";
        initiative.setArea(expected);
        String ans = initiative.getArea();
        assertEquals(expected, ans);
    }

    @Test
    void shouldSetAndGetUser(){
        initiative.setUser(user);
        User ans = initiative.getUser();
        assertEquals(user, ans);
    }

    @Test
    void shouldSetAndGetComments(){
        ArrayList<Comment> expected = new ArrayList<>();
        expected.add(comment1);
        expected.add(comment2);
        initiative.setComments(expected);
        assertEquals(expected, initiative.getComments());
    }

    @Test
    void shouldSetAndGetLikes(){
        String expected = "150";
        initiative.setNumberLikes(expected);
        String ans = initiative.getNumberLikes();
        assertEquals(expected, ans);
    }
}
