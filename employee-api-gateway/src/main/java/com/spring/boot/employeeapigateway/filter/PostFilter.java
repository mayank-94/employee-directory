/**
 * 
 */
package com.spring.boot.employeeapigateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author Mayank
 *
 */
@Component
public class PostFilter extends ZuulFilter {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletResponse response = ctx.getResponse();
		LOGGER.info("Response status -> {}", response.getStatus());
		
		try(InputStream stream = ctx.getResponseDataStream()) {
			String data = CharStreams.toString(new InputStreamReader(stream, CharEncoding.UTF_8));
			LOGGER.info("Response data -> {}", data);
			ctx.setResponseBody(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
