package com.example.adminservlet.logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.example.adminservlet.core", "com.example.adminservlet.logger"})
public class AppConfig {

}