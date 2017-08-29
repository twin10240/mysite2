package com.bigdata2017.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class EncodingFilter implements Filter {
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Encoding Filter Initialized...");
		
		encoding = fConfig.getInitParameter("encoding");
		if (encoding == null) {
			encoding = "utf-8";
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* request */
		request.setCharacterEncoding("utf-8");

		chain.doFilter(request, response);
		
		/* response */
	}

	public void destroy() {

	}
}
