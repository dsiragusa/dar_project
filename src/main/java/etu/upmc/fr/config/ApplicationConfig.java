package etu.upmc.fr.config;

import static org.springframework.context.annotation.ComponentScan.Filter;

import etu.upmc.fr.entity.Account;
import etu.upmc.fr.repository.AccountRepository;
import etu.upmc.fr.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import etu.upmc.fr.Application;

@EnableAsync
@Configuration
@PropertySource("classpath:persistence.properties")
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
@Import(MailConfig.class)
class ApplicationConfig {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Bean
	public AccountRepository accountRepository() {
		return accountRepository;
	}

	@Bean
	public ServiceRepository serviceRepository() {
		return serviceRepository;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}