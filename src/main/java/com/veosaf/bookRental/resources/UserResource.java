package com.veosaf.bookRental.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/user")
public interface UserResource {

	@GET
	@Produces("application/json")
	Response getByCredentials(@QueryParam("username") String username, @QueryParam("password") String password);

}
