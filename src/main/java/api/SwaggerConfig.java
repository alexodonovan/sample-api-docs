package api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().contact(new Contact("Robert Tulley", "", "rtulley@fexco.com")).description(
				"API for adding or editing a customer to the central repository of SS names and addresses. GET and DELETE operations are provided for administrative purposes.")
				.build();

		// return new ApiInfo("The Customer API", "Add or update a SCD customer",
		// "v1.0.0", "", "Robert Tulley",
		// "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	}

}