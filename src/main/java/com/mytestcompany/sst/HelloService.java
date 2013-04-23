package com.mytestcompany.sst;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface HelloService {

	@Path("/sayHello")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String sayHello(@QueryParam("name") String name);
}
