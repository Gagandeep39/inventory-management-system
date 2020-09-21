/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 19:00:02
 * @modify date 2020-09-20 19:00:02
 * @desc Auth Service Impl
 */
package com.cg.inventoryauthservice.service.implementation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cg.inventoryauthservice.dto.ChangePasswordRequest;
import com.cg.inventoryauthservice.dto.LoginRequest;
import com.cg.inventoryauthservice.dto.RegisterRequest;
import com.cg.inventoryauthservice.dto.UpdateRequest;
import com.cg.inventoryauthservice.dto.UserDetailsDto;
import com.cg.inventoryauthservice.entity.Address;
import com.cg.inventoryauthservice.entity.User;
import com.cg.inventoryauthservice.entity.UserDetails;
import com.cg.inventoryauthservice.exception.InvalidCredentialException;
import com.cg.inventoryauthservice.helper.UserDetailsMapper;
import com.cg.inventoryauthservice.repository.AddressRepository;
import com.cg.inventoryauthservice.repository.UserDetailsRepository;
import com.cg.inventoryauthservice.repository.UserRepository;
import com.cg.inventoryauthservice.service.AuthService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

  private UserDetailsRepository userDetailsRepository;
  private AddressRepository addressRepository;
  private UserRepository userRepository;

  @Override
  public Map<String, String> login(LoginRequest loginRequest) {
    return Collections.singletonMap("userId",
        findUserByCredentials(loginRequest.getUsername(), loginRequest.getPassword()).getUserId().toString());
  }

  @Override
  public Map<String, String> register(RegisterRequest registerRequest) {
    checkIfUsernameExists(registerRequest.getUsername());
    UserDetails userDetails = userDetailsRepository.save(UserDetailsMapper.registerToUserDetails(registerRequest));
    Address address = registerRequest.getAddress();
    address.setUserDetails(userDetails);
    addressRepository.save(address);
    return Collections.singletonMap("success", "User created with ID: " + userDetails.getUserDetailsId());
  }

  @Override
  public Map<String, String> changePassword(ChangePasswordRequest changePasswordRequest) {
    User user = findUserByCredentials(changePasswordRequest.getUsername(), changePasswordRequest.getOldPassword());
    user.setPassword(changePasswordRequest.getNewPassword());
    userRepository.save(user);
    return Collections.singletonMap("success", "Successfully Updated Password");
  }

  @Override
  public Map<String, String> updateUser(UpdateRequest updateRequest) {
    UserDetails userDetails = UserDetailsMapper.updateRequestToUserDetails(updateRequest);
    userDetailsRepository.save(userDetails);
    return Collections.singletonMap("success", "Successfully Updated user with ID: " + updateRequest.getUserId());
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserDetailsDto> fetchAllUsers() {
    return userDetailsRepository
      .findAll()
      .stream()
      .map(UserDetailsMapper::userDetailsToDto)
      .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetailsDto fetchUserById(Long id) {
    UserDetails userDetails =  userDetailsRepository
      .findById(id)
      .orElseThrow(() -> new InvalidCredentialException("userId", "ID " + id + " doesn't exist"));
    return UserDetailsMapper.userDetailsToDto(userDetails);
  }

  @Transactional(readOnly = true)
  public User findUserByCredentials(String username, String password) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new InvalidCredentialException("username", "User " + username + " doesn't exist"));
    if (!user.getPassword().equals(password))
      throw new InvalidCredentialException("password", "Invalid Password");
    return user;
  }

  @Override
  public boolean checkIfUsernameExists(String username) {
    if(!userRepository.existsByUsername(username))
      return false;
    else throw new InvalidCredentialException("username", "Username already exists");
  }

}
