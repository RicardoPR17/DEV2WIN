package com.dev2win.iniciativas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.dev2win.iniciativas.data.users.Profile;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfileTest {

    @Test
    void getProfileOnString() {
        assertEquals("Profesor", Profile.PROFESOR.getValue());
        assertEquals("Directivo", Profile.DIRECTIVO.getValue());
        assertEquals("Estudiante", Profile.ESTUDIANTE.getValue());
    }

    @Test
    void getProfileByHisValue() {
        assertEquals(Profile.PROFESOR, Profile.findByValue("Profesor"));
        assertEquals(Profile.PROFESOR, Profile.findByValue("profesor"));
        assertEquals(Profile.PROFESOR, Profile.findByValue("PROFESOR"));
        assertEquals(Profile.ESTUDIANTE, Profile.findByValue("esTUdiANte"));
        assertEquals(Profile.DIRECTIVO, Profile.findByValue("dIrEcTiVo"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", ",", "profesores", "docente" })
    void getNullGivenWrongProfile(String input) {
        assertNull(Profile.findByValue(input));
    }
}
