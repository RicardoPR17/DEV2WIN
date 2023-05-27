package com.dev2win.iniciativas;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.users.Profile;
import com.dev2win.iniciativas.data.users.Role;
import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.likes.Upvote;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpvoteTest {
    @ParameterizedTest
    @ValueSource(longs = { 5L, 17L, 8L, 10L })
    void shouldSetUpvoteId(Long input) {
        User user = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        Initiative initiative = new Initiative("test idea", State.OPEN, "a", "b", "c", user);
        Upvote upvote = new Upvote(initiative, user);
        upvote.setUpvoteId(input);
        assertEquals(input, upvote.getUpvoteId());
    }

    @Test
    void shouldSetUser() {
        User user = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        Initiative initiative = new Initiative("test idea", State.OPEN, "a", "b", "c", user);
        Upvote upvote = new Upvote(initiative, user);
        User anotherUser = new User("Juan", "def", Role.PROPONENTE, "", Profile.ESTUDIANTE, "juan@mail.com");
        upvote.setUser(anotherUser);
        assertEquals(anotherUser, upvote.getUser());
    }

    @Test
    void shouldGiveUser() {
        User user = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        Initiative initiative = new Initiative("test idea", State.OPEN, "a", "b", "c", user);
        Upvote upvote = new Upvote(initiative, user);
        assertEquals(user, upvote.getUser());
    }

    @Test
    void shouldSetInitiative() {
        User user = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        Initiative initiative = new Initiative("test idea", State.OPEN, "a", "b", "c", user);
        Upvote upvote = new Upvote(initiative, user);
        Initiative anotherInitiative = new Initiative("another project", State.REVIEW, "d", "e", "f", user);
        upvote.setInitiative(anotherInitiative);
        assertEquals(anotherInitiative, upvote.getInitiative());
    }
    @Test
    void shouldGiveInitiative() {
        User user = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        Initiative initiative = new Initiative("test idea", State.OPEN, "a", "b", "c", user);
        Upvote upvote = new Upvote(initiative, user);
        assertEquals(initiative, upvote.getInitiative());
    }
}
