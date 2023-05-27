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
    void getAreaOnString() {
        assertEquals("Environment", Area.ENVIRONMENT.getValue());
        assertEquals("Animal rights", Area.ANIMALS.getValue());
    }

    @Test
    void getAreaByHisValue() {
        assertEquals(Area.UNDERTAKING, Area.findByValue("undertaking"));
        assertEquals(Area.UNDERTAKING, Area.findByValue("UNDERTAKING"));
        assertEquals(Area.UNDERTAKING, Area.findByValue("UnDeRtAkInG"));
        assertEquals(Area.IA, Area.findByValue("ArTiFiCiAl InTeLliGeNcE"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "null", "", "IA" })
    void getNullGivenBadArea(String input) {
        assertNull(Area.findByValue(input));
    }
}
