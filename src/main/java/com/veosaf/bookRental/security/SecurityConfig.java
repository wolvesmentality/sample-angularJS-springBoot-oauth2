package com.veosaf.bookRental.security;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private UnauthorizedEntryPoint unauthorisedEntryPoint;

	@Inject
	private StatelessAuthenticationFilter statelessAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //
				.csrf().disable() //
				.authorizeRequests() //
				// autorize preflight request (use
				// springframework.http.HttpMethod)
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //
				.antMatchers("/", "rest/appsetting/*", "/rest/authentication/login").permitAll() //
				.antMatchers("/**").authenticated() //
				.and().exceptionHandling().authenticationEntryPoint(unauthorisedEntryPoint)//
				.and().addFilterBefore(statelessAuthenticationFilter, BasicAuthenticationFilter.class);
	}

	@Inject
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(getAuthenticationProvider());
	}

	@Bean
	public CustomAuthenticationProvider getAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
