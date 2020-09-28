package it.sample.services.department.filter;

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
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

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
	public void filter(ContainerRequestContext containerRequestContext) {

		MDC.put("MYTID", new TID().toString());

		final String method = request.method().toString(); 
		final String path = info.getPath();
		final String address = request.remoteAddress().toString();
		final String hostname = request.remoteAddress().host();

		log.info("Request ["+method+"] ["+path+"] from ["+address+"] ["+hostname+"]", method, path, address, hostname);  

	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		MultiMap headers = request.headers();
		MultiMap params = request.params();
		MultiMap formAttributes = request.formAttributes();
		   
		log.info("-- Form Attribute -- Size ["+formAttributes.size()+"]");
		final StringBuffer sbForm = new StringBuffer();
		formAttributes.forEach(entry -> sbForm.append(entry.getKey()+" "+entry.getValue()+" "));
		log.info(sbForm.toString());
		
		log.info("-- Http Header -- Size ["+headers.size()+"]");
		final StringBuffer sbHeader = new StringBuffer();
		headers.forEach(entry -> sbHeader.append(entry.getKey()+" "+entry.getValue()+" "));
		log.info(sbHeader.toString());
		
		log.info("-- Http Params -- Size ["+params.size()+"]");
		final StringBuffer sbParam = new StringBuffer();
		params.forEach(entry -> sbParam.append(entry.getKey()+" "+entry.getValue()+" "));
		log.info(sbParam.toString());
		
		final int httpStatusCode = response.getStatusCode();
		final String httpStatusMessage = response.getStatusMessage();

		log.info("StatusCode ["+httpStatusCode+"] StatusMessage ["+httpStatusMessage+"]");
		log.info("Response entity ["+responseContext.getEntity()+"]");
		
	}

}
