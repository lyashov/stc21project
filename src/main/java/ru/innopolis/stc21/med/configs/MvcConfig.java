package ru.innopolis.stc21.med.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/").setViewName("medical_history");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/operations").setViewName("operations");
        registry.addViewController("/typeoperations").setViewName("typeoperations");

    }

}