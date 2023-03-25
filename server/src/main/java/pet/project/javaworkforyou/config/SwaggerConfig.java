package pet.project.javaworkforyou.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(@Value("The app for search job") String appDescription,
                                 @Value("0.0.1-SNAPSHOT") String appVersion) {
        return new OpenAPI().info(new Info().title("Work-for-you")
                .version(appVersion)
                .description(appDescription));
    }
}
