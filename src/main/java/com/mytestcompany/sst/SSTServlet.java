/**
 * Copyright 2011 My Test Company, Inc. All rights reserved.
 * This software is the proprietary information of My Test Company.
 * Use is subject to license terms.
 *  
 * Created on Jul 26, 2011
*/
package com.mytestcompany.sst;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gcollins
 *
 */
public class SSTServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3436383118755013105L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("User Principle = " + request.getUserPrincipal() + ", AuthType = " + request.getAuthType());
		response.getWriter().print("Hello OSGi World Servlet. User Principle = " + request.getUserPrincipal());
	}
	
	

}
