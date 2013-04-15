package com.mytestcompany.sst;

import java.util.Collection;

import javax.security.auth.Subject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.security.SimpleGroup;
import org.apache.cxf.interceptor.security.DefaultSecurityContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextFromSpringAuthenticationInterceptor extends AbstractPhaseInterceptor<Message> {

	Log									log	= LogFactory.getLog(SecurityContextFromSpringAuthenticationInterceptor.class);

	private final AuthenticationManager	authenticationmanager;

	public SecurityContextFromSpringAuthenticationInterceptor(AuthenticationManager authenticationManager) {
		super(Phase.PRE_INVOKE);
		this.authenticationmanager = authenticationManager;
	}

	@Override
	public void handleMessage(Message message) {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				if (!authentication.isAuthenticated()) {
					authentication = authenticationmanager.authenticate(authentication);
				}
				onSuccessfulAuthentication(message, authentication);
			} else {
				log.warn("Authentication is not set");
			}
		} catch (AuthenticationException e) {
			log.info("Authentication failed", e);
			throw e;
		}
	}

	private void onSuccessfulAuthentication(Message message, Authentication authentication) {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		createCXFSecurityContext(message, authentication);
	}

	private void createCXFSecurityContext(Message message, Authentication authentication) {
		Subject subject = createSubject(authentication);
		subject.setReadOnly();
		SecurityContext sc = new DefaultSecurityContext(subject);
		message.put(SecurityContext.class, sc);
	}

	private Subject createSubject(Authentication authentication) {
		try {
			Subject subject = new Subject();

			subject.getPrincipals().add(authentication);

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				subject.getPrincipals().add(new SimpleGroup(authority.getAuthority(),
						authentication.getName()));
			}

			return subject;
		} catch (AuthenticationException ex) {
			throw new SecurityException(ex);
		}
	}
}