package uk.ac.ebi.phis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("uk.ac.ebi.phis.controller")
public class SwaggerConfig {

	 
	@Bean
    public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
	         .select()
	         .apis(RequestHandlerSelectors.any())
	         .paths(PathSelectors.regex("/rest/data/.*"))
	         .build()
	         .apiInfo(apiInfo());
	 }
	 	 
	 private ApiInfo apiInfo() {
		 ApiInfo apiInfo = new ApiInfo("PhIS Swagger tests", "description", "version", "termsOfServiceUrl", "contact", "license", "licenseUrl");
	        return apiInfo;
	    }
}
