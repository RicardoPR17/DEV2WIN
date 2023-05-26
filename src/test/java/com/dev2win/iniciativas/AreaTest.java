package com.dev2win.iniciativas;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.dev2win.iniciativas.data.ideas.Area;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AreaTest {
    @Test
    void shuoldGetAreaValue() {
        assertEquals("Environment", Area.ENVIRONMENT.getValue());
        assertEquals("Articial Intelligence", Area.IA.getValue());
        assertEquals("Undertaking", Area.UNDERTAKING.getValue());
        assertEquals("Animal rights", Area.ANIMALS.getValue());
        assertEquals("Healthy life", Area.HEALTHY.getValue());
    }

    @Test
    void shouldGetAreaByValue() {
        assertEquals(Area.ENVIRONMENT, Area.findByValue("Environment"));
        assertEquals(Area.IA, Area.findByValue("Articial Intelligence"));
        assertEquals(Area.UNDERTAKING, Area.findByValue("UnderTAKING"));
        assertEquals(Area.ANIMALS, Area.findByValue("anImaL riGhtS"));
        assertEquals(Area.HEALTHY, Area.findByValue("Healthy lIFe"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "null", "", "animales", "salud" })
    void getNullGivenBadRole(String input) {
        assertNull(Area.findByValue(input));
    }
}
