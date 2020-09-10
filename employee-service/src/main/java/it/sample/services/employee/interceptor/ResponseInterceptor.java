package it.sample.services.employee.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ResponseInterceptor implements WriterInterceptor {

	private static final Logger log = LoggerFactory.getLogger(ResponseInterceptor.class);

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
    	
    	OutputStream originalStream = context.getOutputStream();
        
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
    	context.setOutputStream(baos);
        
    	try {
            context.proceed();
            
        } finally {
        	
            log.info("Response Body: "+baos.toString("UTF-8"));
            
            baos.writeTo(originalStream);
            
            baos.close();
            
            context.setOutputStream(originalStream);
        
        }
    }

}