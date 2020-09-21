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

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  @ApiOperation(value = "PerformLogin")
  @ApiResponse(responseCode = "202", description = "Sucessully Logged In")
  public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.login(loginRequest));
  }

  @PostMapping("/register")
  @ApiOperation(value = "Create an Account")
  @ApiResponse(responseCode = "201", description = "Sucessully Registred")
  public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest registerRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
  }

  @PutMapping("/credentials")
  @ApiOperation(value = "Change Password")
  @ApiResponse(responseCode = "201", description = "Password Updated")
  public ResponseEntity<Map<String, String>> changePassword(
      @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.changePassword(changePasswordRequest));
  }

  @PutMapping("/update")
  @ApiOperation(value = "Update Account")
  @ApiResponse(responseCode = "201", description = "Updated Account")
  public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UpdateRequest updateRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.updateUser(updateRequest));
  }

  @GetMapping
  @ApiOperation(value = "Fetch All users")
  @ApiResponse(responseCode = "200", description = "Fecthed Data")
  public ResponseEntity<List<UserDetailsDto>> fetchAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(authService.fetchAllUsers());
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Fetch User by ID")
  @ApiResponse(responseCode = "200", description = "Fetched by ID")
  public ResponseEntity<UserDetailsDto> fetchUserById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(authService.fetchUserById(id));
  }

  @GetMapping("/forgotpassword/{username}")
  @ApiOperation(value = "Request for secret question")
  @ApiResponse(responseCode = "202", description = "Fetched question")
  public ResponseEntity<Map<String, String>> forgotPassword(@PathVariable String username) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.fetchSecurityQuestionForUser(username));
  }

  @PutMapping("/forgotpassword")
  @ApiOperation(value = "Create new password")
  @ApiResponse(responseCode = "201", description = "Updated Password")
  public ResponseEntity<Map<String, String>> validateAnswerAndUpdatePassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.validateAnswerAndUpdate(forgotPasswordRequest));
  }

}
