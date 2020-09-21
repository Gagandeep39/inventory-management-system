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
import com.cg.inventoryauthservice.dto.ForgotPasswordRequest;
import com.cg.inventoryauthservice.dto.LoginRequest;
import com.cg.inventoryauthservice.dto.RegisterRequest;
import com.cg.inventoryauthservice.dto.UpdateRequest;
import com.cg.inventoryauthservice.dto.UserDetailsDto;
import com.cg.inventoryauthservice.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.status(HttpStatus.FOUND).body(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest registerRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
  }

  @PutMapping("/credentials")
  public ResponseEntity<Map<String, String>> changePassword(
      @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.changePassword(changePasswordRequest));
  }

  @PutMapping("/update")
  public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UpdateRequest updateRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.updateUser(updateRequest));
  }

  @GetMapping
  public ResponseEntity<List<UserDetailsDto>> fetchAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(authService.fetchAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDetailsDto> fetchUserById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(authService.fetchUserById(id));
  }

  @GetMapping("/forgotpassword/{username}")
  public ResponseEntity<Map<String, String>> forgotPassword(@PathVariable String username) {
    return ResponseEntity.status(HttpStatus.FOUND).body(authService.fetchSecurityQuestionForUser(username));
  }

  @PutMapping("/forgotpassword")
  public ResponseEntity<Map<String, String>> validateAnswerAndUpdatePassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.validateAnswerAndUpdate(forgotPasswordRequest));
  }

}
