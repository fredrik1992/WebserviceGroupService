package se.sensera.edu.grouprest.grouprest;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class OpenDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info().title("Sensera Education Group Rest API")
                        .description("### Simple group API for education and teaching. \n" +
                                "To access this resource you need to acquire a token from [Sensera auth](https://iam.sensera.se) \n" +
                                "\n" +
                                "    Realm: test\n" +
                                "    Client Id: group-api\n" +
                                "    Username: user\n" +
                                "    Password: djnJnPf7VCQvp3Fc\n" +
                                "\n" +
                                "[About the keycloak api](https://www.keycloak.org/docs-api/9.0/rest-api/index.html)\n" +
                                "\n" +
                                "[Source can be found at](https://gitlab.com/sensera-education-public/Groups-Rest)")
                        .version("v1.0.0")
                        .license(new License().name("GNU General Public License v3.0").url("https://www.gnu.org/licenses/gpl-3.0.html")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
   /* private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/myapi";
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
//"group-api", "user", "djnJnPf7VCQvp3Fc"
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Sensera Education Group Rest API",
                "### Simple group API for education and teaching. \n" +
                        "To access this resource you need to acquire a token from https://iam.sensera.se \n" +
                        "\n" +
                        "    Realm: test\n" +
                        "    Client Id: group-api\n" +
                        "    Username: user\n" +
                        "    Password: djnJnPf7VCQvp3Fc\n" +
                        "\n" +
                        "**IMPORTANT!!!*** When you Authorize with jwt token below you need to add **Bearer** + **space** in front of the token\n" +
                        "\n" +
                        "About the keycloak api: \n" +
                        "https://www.keycloak.org/docs-api/9.0/rest-api/index.html\n" +
                        "\n" +
                        "Source can be found at:\n" +
                        "https://gitlab.com/sensera-education-public/Groups-Rest",
                "1.0",
                "Free to use",
                new Contact("Christian Mossberg", "sensera.se", "christian.mossberg@sensera.se"),
                "GNU General Public License v3.0",
                "https://www.gnu.org/licenses/gpl-3.0.html",
                Collections.emptyList());
    }*/
}
