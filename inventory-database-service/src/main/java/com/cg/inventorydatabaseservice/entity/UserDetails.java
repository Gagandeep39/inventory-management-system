/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-19 18:33:13
 * @modify date 2020-09-19 18:33:13
 * @desc User details Entity
 */
package com.cg.inventorydatabaseservice.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.cg.inventorydatabaseservice.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetails {

  @Id
  private Long userDetailsId;

  @Column(length = 20)
  private String designation;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  private Date dob;
  @Column(length = 50)
  private String emailId;
  @Column(length = 100)
  private String address;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "userDetailsId", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "FK_USER_ID"))
  @MapsId
  private User user;

}
