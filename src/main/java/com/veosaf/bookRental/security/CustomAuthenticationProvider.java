package com.veosaf.bookRental.security;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.services.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("Bad Credentials");
		}
		UserDto user = userService.getByCredentials(username, password);
		if (user == null) {
			throw new BadCredentialsException("User not found");
		}
		String token = UUID.randomUUID().toString();
		UserToken userToken = new UserToken();
		userToken.setToken(token);
		return new UsernamePasswordAuthenticationToken(userToken, password, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
