package com.veosaf.bookRental.resources.impl;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.veosaf.bookRental.resources.CORSResponseFilter;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {

		// Add filters
		register(CORSResponseFilter.class);

		// Add Resources
		register(AuthenticationResourceImpl.class);
		register(UserResourceImpl.class);
		register(BookResourceImpl.class);
		register(AppSettingResourceImpl.class);

	}

}