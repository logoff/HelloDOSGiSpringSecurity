package com.mytestcompany.sst;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BasicHTTPSpringAuthenticationProvider implements RequestHandler {

	Log	log	= LogFactory.getLog(BasicHTTPSpringAuthenticationProvider.class);

	@Override
	public Response handleRequest(Message message, ClassResourceInfo resourceClass) {

		Authentication authentication = null;

		// CXF HTTP Transport adds decoded Basic Authentication credentials into an instance of
		// AuthorizationPolicy extension and sets it on the current message.
		// Source: https://cxf.apache.org/docs/secure-jax-rs-services.html#SecureJAX-RSServices-Authentication
		AuthorizationPolicy policy = (AuthorizationPolicy) message.get(AuthorizationPolicy.class);
		if (policy != null) {
			String username = policy.getUserName();
			String password = policy.getPassword();

			authentication = createAuthentication(username, password);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// let the request continue
		return null;
	}

	private Authentication createAuthentication(String username, String password) {
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username, password);
		return result;
	}
}