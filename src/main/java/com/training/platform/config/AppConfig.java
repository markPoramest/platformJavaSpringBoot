package com.training.platform.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.*;
@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean registration() {
        OrderedHiddenHttpMethodFilter filter = new OrderedHiddenHttpMethodFilter(); // or inject it with a @Qualifier
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
    @Profile("local")
    @Configuration
    @PropertySource("application-local.properties")
    static class Local{}
    @Profile("alpha")
    @Configuration
    @PropertySource("application-alpha.properties")
    static class Alpha{}
    @Profile("staging")
    @Configuration
    @PropertySource("application-staging.properties")
    static class Staging{}
}