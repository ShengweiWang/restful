package com.example.jersey.service;

import com.example.jersey.model.StockData;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.util.JSONPObject;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Shengwei_Wang on 11/20/16.
 */
public class ServerTest extends JerseyTest {
    private final String uri = "http://localhost:8080";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        new YahooDataServer().init();
    }

    @Override
    protected Application configure() {
        Application ap = new ResourceConfig()
                .packages("com.example.jersey")
                .register(JspMvcFeature.class)
                .register(JacksonJsonProvider.class)
                .register(JsonProcessingFeature.class);
        return ap;
    }

    @Test
    public void testAddCompany() throws Exception {
        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
        final String company = "fb";
        formData.add("company", company);
        target("/add").request().post(Entity.form(formData));
        Thread.sleep(10000);
        String json = target("/history").queryParam("company", company)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        assertEquals(true, json.length() > 10);
    }

    @Test
    public void testListCompanies() throws Exception {
        String stockData = target("/listcompanies")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        assertNotEquals(null, stockData);
    }

    @Test
    public void testCompanyHistory() throws Exception {
        try {
            final String company = "fb";
            String json = target("/history").queryParam("company", company)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            Thread.sleep(10000);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    public void testDeleteCompany() throws Exception {
        testAddCompany();
        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
        final String company = "fb";
        formData.add("company", company);
        target("/delete").request().post(Entity.form(formData));
        Thread.sleep(10000);
        String json = target("/history").queryParam("company", company)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println(json);
        assertEquals(true, json.length() < 10);
        testListCompanies();
    }
}
//        System.out.println(target.path("/listcompanies").toString());
//        Response response = target("/listcompanies")
//                .request("application/json").get();
//        Response response = target("/listcompanies")
//                .request("application/json").get();
//        String response = target("/listcompanies")
//                .request("application/json").get(String.class);
//        List<StockData> ls = response.getMediaType().toString();

////        target("/add").request().post(new Entity<String>(company));
//        JSONPObject jsonpObject = response.readEntity(JSONPObject.class);
