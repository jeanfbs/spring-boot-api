package br.com.sampleapi.config;

import br.com.sampleapi.util.MessageUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class ApplicationConfig  implements WebMvcConfigurer {

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(new ResourceBundleMessageSource(){
            @Override
            public void setBasename(String basename) {
                super.setBasename("messages");
            }
        });
        return factoryBean;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(MessageUtil.getMessage("openapi.title"))
                        .description(MessageUtil.getMessage("openapi.description"))
                        .version(MessageUtil.getMessage("openapi.version")));
    }

}