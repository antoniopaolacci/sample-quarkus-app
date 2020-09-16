package it.sample.services.employee.filter;

import io.opentracing.Tracer;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import it.sample.utils.TID;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


@Provider
public class LoggingFilter implements ContainerResponseFilter, ContainerRequestFilter {

	 private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

     @Context
     UriInfo info;
     
	 @Inject
	 Tracer tracer;

     @Context
     HttpServerRequest request;

	 @Context
	 HttpServerResponse response;

	 @Override
	 public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		 
		    final int httpStatusCode = response.getStatusCode();
		    final String httpStatusMessage = response.getStatusMessage();
	      
		    log.info("StatusCode ["+httpStatusCode+"] StatusMessage ["+httpStatusMessage+"]");
		    log.info("Response entity ["+responseContext.getEntity()+"]"); 
	}

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
    	
    	MDC.put("MYTID", new TID().toString());
    	
        final String method = request.method().toString(); 
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();
        final String hostname = request.remoteAddress().host();
        
        MultiMap headers = request.headers();
        MultiMap params = request.params();
        MultiMap formAttributes = request.formAttributes();
        
        log.info("Request ["+method+"] ["+path+"] from ["+address+"] ["+hostname+"]", method, path, address, hostname);  
        
        log.info("-- Http Header -- Size ["+headers.size()+"]");
        for (String key : headers.names()) {
        	System.out.print(key + ": " + headers.get(key)+" ");
        }
        
        log.info("-- Http Params -- Size ["+params.size()+"]");
        for (String key : params.names()) {
        	System.out.print(key + ": " + params.get(key)+" ");
        }
        	        
        log.info("-- Form Attribute -- Size ["+formAttributes.size()+"]");
        for (String key : formAttributes.names()) {
        	System.out.print(key + ": " + formAttributes.get(key)+" ");
        }
    	
    }
		 
}
