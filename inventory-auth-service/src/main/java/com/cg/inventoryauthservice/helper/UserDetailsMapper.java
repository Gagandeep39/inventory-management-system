/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 23:32:45
 * @modify date 2020-09-20 23:32:45
 * @desc [description]
 */
package com.cg.inventoryauthservice.helper;

import com.cg.inventoryauthservice.dto.RegisterRequest;
import com.cg.inventoryauthservice.entity.User;
import com.cg.inventoryauthservice.entity.UserDetails;
import com.cg.inventoryauthservice.enums.Gender;

public class UserDetailsMapper {

  public static UserDetails registerToUserDetails(RegisterRequest registerRequest) {
    User user = User.builder()
      .username(registerRequest.getUsername())
      .password(registerRequest.getPassword())
      .role("User")
      .build();
    return UserDetails.builder()
      .user(user)
      .designation(registerRequest.getDesignation())
      .gender(Gender.valueOf(registerRequest.getGender()))
      .dob(registerRequest.getDob())
      .emailId(registerRequest.getEmailId())
      .phoneNo(registerRequest.getPhoneNo())
      .build();
  }
  
}
