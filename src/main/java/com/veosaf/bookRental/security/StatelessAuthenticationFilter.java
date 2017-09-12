package com.veosaf.bookRental.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

	@Inject
	private TokenAuthenticationService tokenAuthenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		SecurityContextHolder.getContext()
				.setAuthentication(tokenAuthenticationService.getAuthentication((HttpServletRequest) request));
		chain.doFilter(request, response);
	}
}
