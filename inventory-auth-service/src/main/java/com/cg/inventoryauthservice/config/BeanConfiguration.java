/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-09-21 13:09:43
 * @modify date 2020-09-21 13:09:43
 * @desc Custom beans
 */
package com.cg.inventoryauthservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class BeanConfiguration {

  /**
   * @api http://localhost:8080/swagger-ui/index.html
   * @api http://localhost:8080/v3/api-docs
   */

  @Bean
  public Docket api() {                
      return new Docket(DocumentationType.SWAGGER_2)          
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("Inventory Management System Auth Service")
      .description("Perform Auth operation such as login, register, change password etc.")
      .license("Apache 2.0")
      .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
      .version("1.0")
      .contact(
        new Contact(
          "Gagandeep Singh", 
          "www.github.com/gagandeep39", 
          "Doesn't Exist")
      )
      .build();
  }

}
