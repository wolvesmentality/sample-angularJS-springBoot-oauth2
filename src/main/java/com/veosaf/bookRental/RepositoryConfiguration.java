package com.veosaf.bookRental;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.veosaf.bookRental.repository.custom")
@EnableJpaRepositories(basePackages = { "com.veosaf.bookRental.repository" })
@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:bookRental-dao.properties"),
		@PropertySource(value = "classpath:bookRental-dao-local.properties", ignoreResourceNotFound = true) })
public class RepositoryConfiguration {

}
