package com.dev2win.iniciativas;

import static org.mockito.Mockito.when;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.likes.*;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpvoteServiceTest {

    @Mock
    private UpvoteRepository upvoteRepository;
    @InjectMocks
    private UpvoteService upvoteService;
    private Upvote upvote;

    @BeforeAll
    void setup() {
        MockitoAnnotations.initMocks(this);
        User user = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        user.setUserId(1L);
        Initiative initiative = new Initiative("test idea", State.OPEN, "a", "b", "c", user);
        initiative.setInitiativeId(5L);
        upvote = new Upvote(initiative, user);
        when(upvoteRepository.save(upvote)).thenReturn(upvote);
    }

    @Test
    void savedUpvoteIsSuccessfullyCreated() {
        Upvote newUpvote = upvoteService.addUpvote(upvote);
        assertNotNull(newUpvote.getUpvoteId());
    }

    @Test
    void getsInitiativeUpvotesCorrectly() {
        User user1 = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        user1.setUserId(1L);
        User user2 = new User("Juan", "uwu", Role.PROPONENTE, "", Profile.ESTUDIANTE, "juan@mail.com");
        user2.setUserId(3L);
        Initiative initiative1 = new Initiative("test idea", State.OPEN, "a", "b", "c", user2);
        initiative1.setInitiativeId(5L);
        Initiative initiative2 = new Initiative("another test", State.OPEN, "a", "f", "p", user1);
        initiative2.setInitiativeId(8L);
        Upvote upvote1 = new Upvote(initiative1, user1);
        Upvote upvote2 = new Upvote(initiative2, user1);
        Upvote upvote3 = new Upvote(initiative1, user2);
        Upvote upvote4 = new Upvote(initiative2, user2);
        upvoteService.addUpvote(upvote1);
        upvoteService.addUpvote(upvote2);
        upvoteService.addUpvote(upvote3);
        upvoteService.addUpvote(upvote4);
        List<Upvote> results = upvoteService.getInitiativeUpvotes(initiative2);
        assertEquals(upvote2, results.get(0));
        assertEquals(upvote4, results.get(1));
    }

    @Test
    void getsCountCorrectly() {
        User user1 = new User("Equis", "abc", Role.PROPONENTE, "", Profile.ESTUDIANTE, "x@escuelaing.edu.co");
        user1.setUserId(1L);
        User user2 = new User("Juan", "uwu", Role.PROPONENTE, "", Profile.ESTUDIANTE, "juan@mail.com");
        user2.setUserId(3L);
        Initiative initiative1 = new Initiative("test idea", State.OPEN, "a", "b", "c", user2);
        initiative1.setInitiativeId(5L);
        Initiative initiative2 = new Initiative("another test", State.OPEN, "a", "f", "p", user1);
        initiative2.setInitiativeId(8L);
        Upvote upvote1 = new Upvote(initiative1, user1);
        Upvote upvote2 = new Upvote(initiative2, user1);
        Upvote upvote3 = new Upvote(initiative1, user2);
        Upvote upvote4 = new Upvote(initiative2, user2);
        upvoteService.addUpvote(upvote1);
        upvoteService.addUpvote(upvote2);
        upvoteService.addUpvote(upvote3);
        upvoteService.addUpvote(upvote4);
        assertEquals(2, upvoteService.getInitiativeUpvoteCount(initiative1));
    }

}