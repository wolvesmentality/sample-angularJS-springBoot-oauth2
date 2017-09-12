package com.veosaf.bookRental.security;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public final class TokenHandler {

	public UserToken parseFromToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			try {
				return (UserToken) new ObjectMapper().readValue(token, UserToken.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
