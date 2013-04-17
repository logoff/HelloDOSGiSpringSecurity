package com.mytestcompany.sst;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class BasicExceptionMapper implements ExceptionMapper<Exception> {
	public Response toResponse(Exception exception) {
		Response.Status status;
		if (exception instanceof AccessDeniedException) {
			// This means that the principal doesn't have the required authority and we should return 403.
			status = Response.Status.FORBIDDEN;
		} else if (exception instanceof AuthenticationException) {
			// This means that the client could not be authenticated. In this case the client may want to
			// send (new) credentials and we should return 401.
			status = Response.Status.UNAUTHORIZED;
		} else {
			// Everything else is a server problem.
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return Response.status(status).build();
	}
}
