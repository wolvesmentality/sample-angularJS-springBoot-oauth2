package com.veosaf.bookRental.security;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	@Inject
	private TokenHandler tokenHandler;

	public Authentication getAuthentication(HttpServletRequest request) {
		
		final String token = request.getHeader(AUTH_HEADER_NAME);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || (authentication instanceof AnonymousAuthenticationToken)) {
			if (token != null) {
				UserToken userToken = tokenHandler.parseFromToken(token);
				if (userToken != null) {
					authentication = new UsernamePasswordAuthenticationToken(userToken, null, null);
				}
			}
		}

		return authentication;
	}
}