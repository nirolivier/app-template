package com.niro.test.web.rest;

import com.niro.constants.AuthorityConstant;
import com.niro.dto.UserDto;
import com.niro.services.UserService;
import com.niro.utils.JsonUtils;
import com.niro.web.models.UserModel;
import com.niro.web.rest.UserAccountRestController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link com.niro.web.rest.UserAccountRestController UserAccountRestController} class.
 * @author Olivier nirina
 * @since 1.0
 */

public class UserAccountRestControllerTest extends AbstractRestTest{

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private UserService userServiceMock;

    @Before public void init(){
        MockitoAnnotations.initMocks(this);
        UserAccountRestController userAccountRestController = new UserAccountRestController();
        ReflectionTestUtils.setField(userAccountRestController,"userService", userServiceMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userAccountRestController).build();
    }

    @After public void finish(){

    }

    @Test
    public void signUp() throws Exception {
        UserModel model = new UserModel("user1","password","user1","user1","user1@gmail.com","en",false,new HashSet<>(Arrays.asList(AuthorityConstant.USER)));

        when(userServiceMock.findOneByUsername("user1")).thenReturn(Optional.empty());
        when(userServiceMock.findOneByEmail("user1@gmail.com")).thenReturn(Optional.empty());
        when(userServiceMock.createUser(anyString(), anyString(),anyString(), anyString(),anyString(), anyString())).thenReturn(any(UserDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtils.convertObjectToByte(model)))
                .andExpect(status().isCreated());

    }

    @Test
    public void signUpBadRequest() throws Exception {
        UserModel modelBad = new UserModel("user1","password","user1","user1","user1@gmail.com","en",false,new HashSet<>(Arrays.asList(AuthorityConstant.USER)));

        when(userServiceMock.findOneByUsername(eq("user1"))).thenReturn(Optional.ofNullable(new UserDto()));
        when(userServiceMock.findOneByEmail(eq("user1@gmail.com"))).thenReturn(Optional.ofNullable(new UserDto()));
        when(userServiceMock.createUser(eq("user1"), eq("password"),eq("user1"), eq("user1"),eq("user1@gmail.com"), eq("en"))).thenReturn(new UserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtils.convertObjectToByte(modelBad)))
                .andExpect(status().isBadRequest());

    }

    @Test public void activateAccount(){

    }
    @Test public void changePassword(){

    }

    @Test public void resetPassword(){

    }

    @Test public void validateNewPassword(){

    }

    @Test public void isAuthenticated(){

    }
}
