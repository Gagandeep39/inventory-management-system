/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-20 19:10:28
 * @modify date 2020-09-20 19:10:28
 * @desc Change password request object
 */
package com.cg.inventoryauthservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Change Password Request", value = "Change Password")
public class ChangePasswordRequest {

  
  @NotBlank
  @Size(min = 5, max = 20)
  @ApiModelProperty(value = "Username")
  private String username;
  @NotBlank
  @Size(min = 5, max = 20)
  @ApiModelProperty(value = "Old Password")
  private String oldPassword;
  @NotBlank
  @Size(min = 5, max = 20)
  @ApiModelProperty(value = "New Password")
  private String newPassword;
  
}
