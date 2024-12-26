package ru.botalov.test_security2db_themeleaf.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

public class MvcConfig {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
