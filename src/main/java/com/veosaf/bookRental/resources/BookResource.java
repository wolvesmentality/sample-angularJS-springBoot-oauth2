package com.veosaf.bookRental.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/book")
public interface BookResource {

	@GET
	@Produces("application/json")
	Response findAll();

	@GET
	@Produces("application/json")
	@Path("/{id}")
	Response findById(@PathParam("id") Long id);

}
