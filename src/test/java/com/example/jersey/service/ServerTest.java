package com.example.jersey.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by Shengwei_Wang on 11/20/16.
 */
public class ServerTest extends JerseyTest{
    private WebTarget target;
    private final String company = "fb";
    private final String uri = "http://localhost:8080";
    @Before
    public void setUp() throws Exception {
        super.setUp();
        target = client().target(uri);
    }
    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.example.jersey");
    }
    @Test
    public void listCompanies() throws Exception {
        Response response = target.path("listcompanies/")
                .request()
                .buildGet()
                .invoke();
        String readEntity = response.readEntity(String.class);
        System.out.println("list Companies:\n" + readEntity);
        response.close();
    }
}