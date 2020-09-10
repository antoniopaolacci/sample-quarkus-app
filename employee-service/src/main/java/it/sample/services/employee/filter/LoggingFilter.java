package it.sample.services.employee.filter;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class LoggingFilter implements ContainerResponseFilter {

	 private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

     @Context
     UriInfo info;

     @Context
     HttpServerRequest request;

	 @Context
	 HttpServerResponse response;

	 @Override
	 public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		 
	        final String method = requestContext.getMethod();
	        final String path = info.getPath();
	        final String address = request.remoteAddress().toString();
	        
	        MultiMap headers = request.headers();
	        MultiMap params = request.params();
	        MultiMap formAttributes = request.formAttributes();
	        
	        log.info("Request ["+method+"] ["+path+"] from IP ["+address+"]", method, path, address);  
	        
	        log.info("-- Http Header -- ");
	        for (String key : headers.names()) {
	        	System.out.println(key + " " + headers.get(key));
	        }
	        
	        log.info("-- Http Params --");
	        for (String key : params.names()) {
	        	System.out.println(key + " " + params.get(key));
	        }
	        
	        log.info("-- Form Attribute --");
	        for (String key : formAttributes.names()) {
	        	System.out.println(key + " " + formAttributes.get(key));
	        }
		
		    final int httpStatusCode = response.getStatusCode();
		    final String httpStatusMessage = response.getStatusMessage();
	      
		    log.info("StatusCode ["+httpStatusCode+"] StatusMessage ["+httpStatusMessage+"]");
		
		    log.info("Response entity ["+responseContext.getEntity()+"]"); 
		
	}
	 
}
