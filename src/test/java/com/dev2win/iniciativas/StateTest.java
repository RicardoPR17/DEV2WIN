package com.dev2win.iniciativas;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.dev2win.iniciativas.data.ideas.State;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StateTest {
    @Test
    void getStateOnString() {
        assertEquals("Closed", State.CLOSED.getValue());
        assertEquals("Aproved", State.APROVED.getValue());
    }

    @Test
    void getStateByHisValue() {
        assertEquals(State.REVIEW, State.findByValue("review"));
        assertEquals(State.REVIEW, State.findByValue("REVIEW"));
        assertEquals(State.REVIEW, State.findByValue("ReViEw"));
        assertEquals(State.OPEN, State.findByValue("OpEn"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "null", "", "Expected to pass" })
    void getNullGivenBadState(String input) {
        assertNull(State.findByValue(input));
    }
}