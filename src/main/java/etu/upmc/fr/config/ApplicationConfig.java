package etu.upmc.fr.config;

import static org.springframework.context.annotation.ComponentScan.Filter;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;

import etu.upmc.fr.Application;

@Configuration
@PropertySource("classpath:persistence.properties")
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
@Import(MailConfig.class)
class ApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}