package com.veosaf.bookRental.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/authentication")
public interface AuthenticationResource {

	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	Response login(@FormParam("login") String login, @FormParam("password") String password);

	@Path("/authorize")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response authorize();

	@Path("/logout")
	@GET
	@Produces("application/json")
	Response logout();

	@Path("/current")
	@GET
	@Produces("application/json")
	Response getCurrentUser();

}
