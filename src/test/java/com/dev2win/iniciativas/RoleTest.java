package com.dev2win.iniciativas;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.dev2win.iniciativas.data.users.Role;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoleTest {

    @Test
    void getRoleOnString() {
        assertEquals("Proponente", Role.PROPONENTE.getValue());
        assertEquals("Administrador", Role.ADMINISTRADOR.getValue());
    }

    @Test
    void getRoleByHisValue() {
        assertEquals(Role.ADMINISTRADOR, Role.findByValue("Administrador"));
        assertEquals(Role.ADMINISTRADOR, Role.findByValue("administrador"));
        assertEquals(Role.ADMINISTRADOR, Role.findByValue("ADMINISTRADOR"));
        assertEquals(Role.PROPONENTE, Role.findByValue("PrOpOnEnTe"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "null", "", "proponenteeeee" })
    void getNullGivenBadRole(String input) {
        assertNull(Role.findByValue(input));
    }
}
