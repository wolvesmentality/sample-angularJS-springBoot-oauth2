package com.veosaf.bookRental.security;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.models.User;
import com.veosaf.bookRental.services.UserService;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Inject
	private UserService userService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http //
				.csrf().disable() //
				.authorizeRequests() //
				.antMatchers("/rest/authentication/test").permitAll();

	}

	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
		config.tokenExtractor(tokenExtractor());
	}

	@Bean
	@Primary
	public ResourceServerTokenServices tokenServices() {
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(
				"https://www.googleapis.com/oauth2/v1/userinfo?alt=json", null) {
			@Override
			protected Object getPrincipal(Map<String, Object> map) {
				String email = (String) map.get("email");
				UserDto user = null;
				UserToken principal = null;
				if (email != null) {
					user = userService.getByEmail(email);
					if (user == null) {
						User newUser = getUser(map);
						user = userService.save(newUser);
					}
				}
				if (user != null) {

				}
				return principal == null ? super.getPrincipal(map) : principal;
			}
		};
		return tokenServices;
	}

	private User getUser(Map<String, Object> map) {

		User newUser = new User();

		return newUser;

	}

	@Bean
	public TokenExtractor tokenExtractor() {
		return new BearerTokenExtractor();
	}

}
