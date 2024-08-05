package com.commerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class CommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
	}



	@Configuration
	public class SwaggerConfig {


		private SecurityScheme createAPIKeyScheme() {
			return new SecurityScheme().type(SecurityScheme.Type.HTTP)
					.bearerFormat("JWT")
					.scheme("bearer");
		}

		/**
		 * Open api open api.
		 *
		 * @return the open api
		 */
		@Bean
		public OpenAPI openAPI() {
			return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
					.components(new Components()
							.addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
					.info(new Info()
							.title("DOCUMENTACION API REST ")
							.description("A continuacion se presenta la documentacion de las apis desarrolladas ")
							.version("1.0")
						.contact(new Contact()
								.name("Anderson Cardozo Arrieta")
								.email("andercardozo04@gmail.com")
								.url("#")
						)
						.license(new License()
								.name("License of API")
						)
					);
		}
	}

}
