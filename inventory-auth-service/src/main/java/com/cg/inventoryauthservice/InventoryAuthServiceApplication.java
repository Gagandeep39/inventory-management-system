/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-25 22:45:07
 * @modify date 2020-09-25 22:45:07
 * @desc Starting point
 */
package com.cg.inventoryauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryAuthServiceApplication.class, args);
	}

}
