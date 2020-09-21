/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-21 19:07:51
 * @modify date 2020-09-21 19:07:51
 * @desc Auth COntroller test cases
 */
package com.cg.inventoryauthservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import com.cg.inventoryauthservice.dto.ChangePasswordRequest;
import com.cg.inventoryauthservice.dto.ForgotPasswordRequest;
import com.cg.inventoryauthservice.dto.LoginRequest;
import com.cg.inventoryauthservice.dto.RegisterRequest;
import com.cg.inventoryauthservice.dto.UpdateRequest;
import com.cg.inventoryauthservice.entity.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void login() throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    LoginRequest loginRequest = new LoginRequest();
    RequestBuilder builder = MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(loginRequest));
    MvcResult result = mockMvc.perform(builder).andReturn();
    assertEquals(400, result.getResponse().getStatus());

    loginRequest.setPassword("123456");
    loginRequest.setUsername("gagan");
    mockMvc
        .perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(loginRequest)))
        .andExpect(status().isAccepted()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userId", Matchers.is("100001")));
  }

  @Test
  void register() throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    // Fix date issue
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    Address address = new Address();
    address.setArea("Earths");
    address.setCity("Mumbai");
    address.setState("Maharashtra");
    address.setPincode("400072");
    RegisterRequest registerRequest = RegisterRequest.builder().username("Pokemon").password("123456").address(address)
        .designation("Salesman").dob(LocalDate.of(1999, 5, 14)).emailId("test@mail.com").phoneNo("9988776655")
        .gender("Male").build();
    mockMvc
        .perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerRequest)))
        .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").exists());
  }

  @Test
  void changePassword() throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.builder().username("gagan")
        .newPassword("123456").oldPassword("123456").build();
    mockMvc
        .perform(MockMvcRequestBuilders.put("/auth/credentials").contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(changePasswordRequest)))
        .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").exists());
  }

  @Test
  void updateUser() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    // Fix date issue
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    Address address = new Address();
    address.setArea("Earths");
    address.setCity("Mumbai");
    address.setState("Maharashtra");
    address.setPincode("400072");
    UpdateRequest updateRequest = UpdateRequest.builder().userId(100001L).address(address).designation("Salesman")
        .dob(LocalDate.of(1999, 5, 14)).emailId("test@mail.com").phoneNo("9988776655").gender("Male")
        .securityQuestion("New question").securityAnswer("answer").build();
    mockMvc
        .perform(MockMvcRequestBuilders.put("/auth/update").contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateRequest)))
        .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").exists());
  }

  @Test
  void fetchAllUsers() throws Exception {
    RequestBuilder builder = MockMvcRequestBuilders.get("/auth");
    MvcResult result = mockMvc.perform(builder).andReturn();
    assertEquals(result.getResponse().getStatus(), 200);
  }

  @Test
  void fetchUserById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/" + 100001).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userId", Matchers.is(100001)));
  }

  @Test
  void forgotPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/forgotpassword/gagan").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.securityQuestion").exists());

  }

  @Test
  void validateAnswerAndUpdatePassword() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ForgotPasswordRequest forgotPasswordRequest = ForgotPasswordRequest.builder().securityAnswer("answer")
        .username("gagan").newPassword("123456").build();
    mockMvc
        .perform(MockMvcRequestBuilders.put("/auth/forgotpassword").contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(forgotPasswordRequest)))
        .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").exists());
  }
}
