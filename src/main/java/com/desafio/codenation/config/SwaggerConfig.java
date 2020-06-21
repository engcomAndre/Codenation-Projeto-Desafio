package com.desafio.codenation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.*;


@Configuration
@EnableSwagger2WebMvc
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {

    private static final String AUTH_SERVER = "http://localhost:8080/app";
    @Value("${security.oauth2.client.clientId}")
    private static String CLIENT_ID = "desafio";
    @Value("${security.oauth2.client.clientSecret}")
    private static String CLIENT_SECRET;
    private final String RESOURCES_PACK = "com.desafio.codenation.resources";
    private final ResponseMessage m201 = customMessage1();
    private final ResponseMessage m204put = simpleMessage(204, "Atualização ok");
    private final ResponseMessage m204del = simpleMessage(204, "Deleção ok");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");
    @Value("${host.full.dns.auth.link}")
    private String authLink;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                .select()
                .apis(RequestHandlerSelectors.basePackage(RESOURCES_PACK))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(securitySchema(),apiKey()))
                .securityContexts(Arrays.asList(securityContext()));
                /*.globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                        .name("Authorization")
                        .description("Acess Token")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()));*/

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/user/**"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Access Token", "Authorization", "header");
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(CLIENT_ID, CLIENT_SECRET, "", "Desafio Codenation", "Token", ApiKeyVehicle.HEADER, "Token Access", " ,");
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        authorizationScopeList.add(new AuthorizationScope("password", "all aceess"));
        List<GrantType> grantTypes = new ArrayList();

        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authLink);

        grantTypes.add(creGrant);

        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API Central de Erros",
                "API Utilizada como projeto final da Aceleração JavaDev da Codenation",
                "Versão 1.0",
                "http://www.apache.org/licenses/LICENSE-2.0.txt",
                new Contact("André Vieira", "https://github.com/engcomAndre", "engcomAndre@gmail.com"),
                "Uso aberto ,não comercial",
                "http://www.apache.org/licenses/LICENSE-2.0.txt",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

    private ResponseMessage customMessage1() {
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
        return new ResponseMessageBuilder()
                .code(201)
                .message("Recurso criado")
                .headersWithDescription(map)
                .build();
    }
}