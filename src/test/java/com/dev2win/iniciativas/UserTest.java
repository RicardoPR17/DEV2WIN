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

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest {

    @ParameterizedTest
    @ValueSource(longs = { 5L, 17L, 8L, 10L })
    void shouldSetUserId(Long input) {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        user.setUserId(input);
        assertEquals(input, user.getUserId());
    }

    @Test
    void shouldGiveUserName() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        assertEquals("Pepe", user.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = { "Daniel", "Santiago", "Jorge", "Eden" })
    void shouldSetUserName(String input) {
        User user = new User(input, "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        assertEquals(input, user.getName());
    }

    @Test
    void shouldGiveUserPassword() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        assertEquals("1234", user.getPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = { "5678", "idea", "@rr0b4" })
    void shouldSetUserPassword(String input) {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        user.setPassword(input);
        assertEquals(input, user.getPassword());
    }

    @Test
    void shouldGiveUserRole() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        assertEquals(Role.PROPONENTE.getValue(), user.getRole());
    }

    @Test
    void shouldSetUserRole() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        user.setRole("Administrador");
        assertEquals(Role.ADMINISTRADOR.getValue(), user.getRole());
    }

    @Test
    void shouldGiveUserState() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "desarrollo", Profile.ESTUDIANTE, "test@test.com");
        assertEquals("desarrollo", user.getState());
    }

    @Test
    void shouldGiveUserProfile() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        assertEquals(Profile.ESTUDIANTE.getValue(), user.getProfile());
    }

    @Test
    void shouldSetUserProfile() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        user.setProfile("Directivo");
        assertEquals(Profile.DIRECTIVO.getValue(), user.getProfile());
    }

    @Test
    void shouldGiveUserEmail() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        assertEquals("test@test.com", user.getMail());
    }

    @ParameterizedTest
    @ValueSource(strings = { "idea.test@mail.comn", "case41@some.com", "cvds@eci.com" })
    void shouldSetUserEmail(String input) {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        user.setMail(input);
        assertEquals(input, user.getMail());
    }

    @Test
    void shouldSetInitiative() {
        User user = new User("Pepe", "1234", Role.PROPONENTE, "", Profile.ESTUDIANTE, "test@test.com");
        Initiative initiative = new Initiative("Hacer renunines antes de las 7", State.OPEN, "", "", "", user);
        ArrayList<Initiative> InitiativeArray = new ArrayList<>();
        InitiativeArray.add(initiative);
        user.setIdeas(InitiativeArray);
        assertEquals(InitiativeArray, user.getIdeas());
    }
}
