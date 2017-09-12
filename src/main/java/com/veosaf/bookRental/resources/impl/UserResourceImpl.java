package com.veosaf.bookRental.resources.impl;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.resources.UserResource;
import com.veosaf.bookRental.services.UserService;

public class UserResourceImpl implements UserResource {

	@Inject
	private UserService userService;

	@Override
	public Response getByCredentials(String username, String password) {

		UserDto userDto = userService.getByCredentials(username, password);
		return Response.ok(userDto).build();
	}

}
