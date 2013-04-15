package com.mytestcompany.sst;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.annotation.Secured;

@Path("/")
public interface HelloService {

	@Path("/sayHello")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Secured({ "ROLE_ADMIN" })
	public String sayHello(@QueryParam("name") String name);
}
