package com.dev2win.iniciativas;

import com.dev2win.iniciativas.data.users.*;
import com.dev2win.iniciativas.faces.AdminBean;
import com.dev2win.iniciativas.faces.FacesContextWrapper;
import com.dev2win.iniciativas.faces.PrimeFacesWrapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminBeanTest {
    @Mock
    private FacesContextWrapper facesContextWrapper;

    @Mock
    private PrimeFacesWrapper primeFacesWrapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminBean adminBean;

    private User user1;
    private User user2;
    private User user;
    private ArrayList<User> users;
    private ArrayList<User> usersT;

    @BeforeAll
    void setup() {
        MockitoAnnotations.initMocks(this);
        user1 = new User("Jorge", "Pass", Role.ADMINISTRADOR, "", Profile.DIRECTIVO, "juuseche@gmail.com");
        user2 = new User("Gonzalo", "Pass", Role.PROPONENTE, "", Profile.ESTUDIANTE, "gonzalo@gmail.com");
        user = new User("Gonzalo", "Pass", Role.PROPONENTE, "", Profile.ESTUDIANTE, "gonzalo@gmail.com");
        users = new ArrayList<User>();
        usersT = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        usersT.add(user2);
        Mocks mocks = new Mocks();
        when(facesContextWrapper.getCurrentInstance()).thenReturn(mocks.facesContextMock);
        when(primeFacesWrapper.current()).thenReturn(mocks.primeFaces);
        when(userService.updateUser(user)).thenReturn(user);
    }

    @Test
    void shouldSetAndGetNewRole(){
        String expected = "Administrador";
        adminBean.setNewRole(expected);
        String ans = adminBean.getNewRole();
        assertEquals(expected, ans);
    }

    @Test
    void shouldSetAndGetUsers(){
        adminBean.setUsers(users);
        assertEquals(users, adminBean.getUsers());
    }

    @Test
    void shouldSetAndGetSelectedUsers(){
        adminBean.setSelectedUsers(users);
        assertEquals(users, adminBean.getSelectedUsers());
    }

    @Test
    void shouldValidateIfThereIsSelectedUsers(){
        adminBean.setSelectedUsers(null);
        assertFalse(adminBean.hasSelectedUsers());
        adminBean.setSelectedUsers(users);
        assertTrue(adminBean.hasSelectedUsers());
    }

    @Test
    void shouldGetUpdateButton(){
        String expected1 = "Update 1 user selected";
        adminBean.setSelectedUsers(usersT);
        assertEquals(expected1, adminBean.getUpdateButtonMessage());
        String expected2 = "Update 2 users selected";
        adminBean.setSelectedUsers(users);
        assertEquals(expected2, adminBean.getUpdateButtonMessage());
    }

    @Test
    void shouldModifyUserRoleIfThereIsAssignedRole(){
        String role = "Administrador";
        adminBean.setNewRole(role);
        assertTrue(adminBean.modifyUserRole());
    }

    @Test
    void shouldNotModifyUserRoleIfThereIsNotAssignedRole(){
        String role = null;
        adminBean.setNewRole(role);
        assertFalse(adminBean.modifyUserRole());
    }
}
