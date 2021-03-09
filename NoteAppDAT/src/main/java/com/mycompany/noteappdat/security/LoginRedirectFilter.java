package com.mycompany.noteappdat.security;

import com.mycompany.noteappdat.model.user.UserBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({"/index"})
public class LoginRedirectFilter extends HttpFilter {
	@Inject private UserBean userBean;
	@Inject private ServletContext servletContext;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (userBean.isLoggedIn()) {
			chain.doFilter(request, response);
		} else {
			System.out.println("filter redirecting!");
			response.sendRedirect(servletContext.getContextPath());
		}
	}
}
