package com.example.jersey.service;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

/**
 * A Resource Configuration Class
 * Trying to register necessary class and package
 */
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        packages("com.example.jersey");
        register(JspMvcFeature.class);
        register(JacksonJsonProvider.class);
        register(JsonProcessingFeature.class);
    }
}
