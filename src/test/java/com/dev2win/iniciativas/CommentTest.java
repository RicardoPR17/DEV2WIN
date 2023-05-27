package com.dev2win.iniciativas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import com.dev2win.iniciativas.data.ideas.*;
import com.dev2win.iniciativas.data.comments.*;
import com.dev2win.iniciativas.data.users.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentTest {
    private User user;
    private User user2;
    private Initiative initiative;
    private Initiative initiative2;

    @BeforeAll
    void setup() {
        user = new User("Rick", "2983", Role.ADMINISTRADOR, "", Profile.PROFESOR, "abc@dev2win.com");
        user.setUserId(1L);
        user2 = new User("Eden", "1337", Role.PROPONENTE, "", Profile.DIRECTIVO, "tboi@dev2win.com");
        user2.setUserId(2L);
        initiative = new Initiative("thing", State.CLOSED, "a", "b", "c", user, Area.HEALTHY);
        initiative2 = new Initiative("some", State.OPEN, "d", "e", "f", user, Area.IA);
    }

    @ParameterizedTest
    @ValueSource(longs = { 4L, 6L, 8L })
    void shouldGetCommentId(Long input) {
        Comment comment = new Comment("test", user, initiative);
        comment.setCommentId(input);
        assertEquals(input, comment.getCommentId());
    }

    @Test
    void shouldGetCommentDate() {
        Comment comment = new Comment("test", user2, initiative2);
        assertEquals(LocalDate.now(), comment.getDate());
    }

    @Test
    void shouldSetCommentDate() {
        Comment comment = new Comment("test", user2, initiative);
        LocalDate date = LocalDate.of(2002, 8, 17);
        comment.setDate(date);
        assertEquals(date, comment.getDate());
    }

    @ParameterizedTest
    @ValueSource(strings = { "test", "comment", "", "idea", "commit" })
    void shouldGetcommentary(String input) {
        Comment comment = new Comment(input, user, initiative2);
        assertEquals(input, comment.getCommentary());
    }

    @ParameterizedTest
    @ValueSource(strings = { "test", "comment", "", "idea", "commit" })
    void shouldSetcommentary(String input) {
        Comment comment = new Comment("Initial", user2, initiative);
        comment.setCommentary(input);
        assertEquals(input, comment.getCommentary());
    }

    @Test
    void shouldGetCommentUser() {
        Comment comment = new Comment("test", user, initiative2);
        assertEquals(user, comment.getUser());
    }

    @Test
    void shouldSetCommentUser() {
        Comment comment = new Comment("test", user, initiative);
        comment.setUser(user2);
        assertEquals(user2, comment.getUser());
    }

    @Test
    void shouldGetInitiative() {
        Comment comment = new Comment("test", user, initiative);
        assertEquals(initiative, comment.getInitiative());
    }

    @Test
    void shouldSetInitiative() {
        Comment comment = new Comment("test", user, initiative);
        comment.setInitiative(initiative2);
        assertEquals(initiative2, comment.getInitiative());
    }

    @Test
    void shouldSetData() {
        Comment comment = new Comment();
        comment.setCommentary("");
        comment.setInitiative(initiative);
        comment.setUser(user2);
        assertNotEquals(null, comment.getCommentary());
    }
}
