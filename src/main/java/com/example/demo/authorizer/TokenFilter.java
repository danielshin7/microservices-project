/**
 * Check which links need authorization code
 */
package com.example.demo.authorizer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.logger.Logger;
import com.example.demo.authorizer.JWTAuthorizer;

@Component
@Order(1)
public class TokenFilter implements Filter{
	private String auth_scope = "com.webage.auth.apis";
	private String api_scope = "com.webage.data.apis";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		
	
		String tokenheader = req.getHeader("tokencheck");
		if( tokenheader != null && !tokenheader.equalsIgnoreCase("true") ) {
			chain.doFilter(request, response);
			return;		
		}
		//disregard token check on these links
		if( !uri.startsWith("/api/register")&& !uri.equals("/api/customers")) {
			chain.doFilter(request, response);
			return;			
		}else{
			//authorization check
			String authheader = req.getHeader("authorization");
			if(authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
				String jwt_token = authheader.substring(7, authheader.length());
				if(JWTAuthorizer.verifyToken(jwt_token)) {
					String request_scopes = JWTAuthorizer.getScopes(jwt_token);
					if(request_scopes.contains(api_scope) || request_scopes.contains(auth_scope)) {
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}		
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");

	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Logger.log("AuthFilter.init");
		
	}

	@Override
	public void destroy() {
		Logger.log("AuthFilter.destroy");	
	}

}