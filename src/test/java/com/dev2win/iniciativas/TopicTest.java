package com.dev2win.iniciativas;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.dev2win.iniciativas.data.ideas.Area;
import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.topic.Topic;
import com.dev2win.iniciativas.data.users.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicTest {

    @ParameterizedTest
    @ValueSource(longs = { 10L, 15L, 20L, 23L })
    void ShouldSetTopicId(Long input) {
        Topic topic = new Topic("Seguridad");
        topic.setTopicId(input);
        assertEquals(input, topic.getTopicId());
    }

    @Test
    void ShouldGiveTopicName() {
        Topic topic = new Topic("Desarrollo");
        assertEquals("Desarrollo", topic.getTopicName());
    }

    @ParameterizedTest
    @ValueSource(strings = { "Desarrollo", "Seguridad", "Pruebas" })
    void ShouldSetTopicName(String input) {
        Topic topic = new Topic(input);
        assertEquals(input, topic.getTopicName());
    }

    @Test
    void ShouldGiveTopicInitiatives() {
        Topic topic = new Topic("Seguridad");

        User user = new User("Carlos", "123", Role.PROPONENTE, "", Profile.ESTUDIANTE, "carlos@test.com");
        Initiative initiative = new Initiative("Revisar seguridad", State.OPEN, "", "", "", user, Area.UNDERTAKING);
        ArrayList<Initiative> initiatives = new ArrayList<>();
        initiatives.add(initiative);

        topic.setInitiatives(initiatives);
        assertEquals(initiatives, topic.getInitiatives());
    }
}
