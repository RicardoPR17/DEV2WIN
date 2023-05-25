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
}
