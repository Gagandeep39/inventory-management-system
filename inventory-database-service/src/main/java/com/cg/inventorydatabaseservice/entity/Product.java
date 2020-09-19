/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-19 19:20:33
 * @modify date 2020-09-19 19:20:33
 * @desc Product properties
 */
package com.cg.inventorydatabaseservice.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

  @Id
  @SequenceGenerator(name = "product_id_sequence", initialValue = 100000, allocationSize = 1)
  @GeneratedValue(generator = "product_id_sequence", strategy = GenerationType.SEQUENCE)
  private Long productId;
  private String materialName;
  private String description;
  private Double quantityAvailable;
  private String quantityUnit;
  @ManyToOne
  @JoinColumn(name = "warehouseId", referencedColumnName = "warehouseId", foreignKey = @ForeignKey(name = "FK_warehouse_ID"))
  private Warehouse warehouse;
}
