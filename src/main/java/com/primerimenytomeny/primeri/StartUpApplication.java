package com.primerimenytomeny.primeri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;



/**
 * Hello world!
 *
 */
@SpringBootApplication
public class StartUpApplication extends SpringBootServletInitializer {
	
	@Autowired 
	private TestData td;

	
	
    public static void main( String[] args ) throws Exception {
    	SpringApplication.run(StartUpApplication.class, args);   	
        //System.out.println( "Hello World!" );
    }
}
