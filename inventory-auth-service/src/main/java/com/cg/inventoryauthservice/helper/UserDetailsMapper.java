/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 23:32:45
 * @modify date 2020-09-20 23:32:45
 * @desc Mapps Entities with Dto
 */
package com.cg.inventoryauthservice.helper;

import com.cg.inventoryauthservice.dto.RegisterRequest;
import com.cg.inventoryauthservice.dto.UserDetailsDto;
import com.cg.inventoryauthservice.entity.Address;
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

  public static UserDetailsDto userDetailsToDto(UserDetails userDetails){
    return UserDetailsDto.builder()
      .userId(userDetails.getUserDetailsId())
      .username(userDetails.getUser().getUsername())
      .role(userDetails.getUser().getRole())
      .phoneNo(userDetails.getPhoneNo())
      .gender(userDetails.getGender().toString())
      .designation(userDetails.getDesignation())
      .dob(userDetails.getDob())
      .emailId(userDetails.getEmailId())
      .address(userDetails.getAddress())
      .build();
  }

  public static UserDetails dtoToUserDetails(UserDetailsDto userDetailsDto) {
    Address address = userDetailsDto.getAddress();
    address.setAddressId(userDetailsDto.getUserId());
    return UserDetails.builder()
      .address(address)
      .userDetailsId(userDetailsDto.getUserId())
      .phoneNo(userDetailsDto.getPhoneNo())
      .gender(Gender.valueOf(userDetailsDto.getGender()))
      .designation(userDetailsDto.getDesignation())
      .dob(userDetailsDto.getDob())
      .emailId(userDetailsDto.getEmailId())
      .build();
  }
  
}
