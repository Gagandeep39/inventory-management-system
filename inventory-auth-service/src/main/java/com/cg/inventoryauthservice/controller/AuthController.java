/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 01:33:44
 * @modify date 2020-09-20 01:33:44
 * @desc Endpoints for Auth routes
 */
package com.cg.inventoryauthservice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cg.inventoryauthservice.dto.ChangePasswordRequest;
import com.cg.inventoryauthservice.dto.LoginRequest;
import com.cg.inventoryauthservice.dto.RegisterRequest;
import com.cg.inventoryauthservice.dto.UserDetailsDto;
import com.cg.inventoryauthservice.service.AuthService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

  private AuthService authService;

  @PostMapping("/login")
  public Map<String, String> login(@Valid @RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @PostMapping("/register")
  public Map<String, String> register(@Valid @RequestBody RegisterRequest registerRequest){
    return authService.register(registerRequest);
  }

  @PutMapping("/credentials")
  public Map<String, String> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest){
    return authService.changePassword(changePasswordRequest);
  }

  @PutMapping("/update")
  public Map<String, String> updateUser(@Valid @RequestBody UserDetailsDto userDetailsDto){
    return authService.updateUser(userDetailsDto);
  }

  @GetMapping
  public List<UserDetailsDto> fetchAllUsers(){
    return authService.fetchAllUsers();
  }

  @GetMapping("/{id}")
  public UserDetailsDto fetchUserById(@PathVariable Long id){
    return authService.fetchUserById(id);
  }

}
