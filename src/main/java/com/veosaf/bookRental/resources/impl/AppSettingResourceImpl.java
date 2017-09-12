package com.veosaf.bookRental.resources.impl;

import javax.ws.rs.core.Response;

import com.veosaf.bookRental.resources.AppSettingResource;

public class AppSettingResourceImpl implements AppSettingResource {

	@Override
	public Response findByName(String name) {
		String version = "1.7";
		return Response.ok(version).build();
	}

}
