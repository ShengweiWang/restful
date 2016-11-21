package com.example.jersey.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shengwei_Wang on 11/19/16.
 */
public class YahooDataServerTest {

    @Test
    public void testAddCompany() throws Exception {
        DataServer dataServer = new YahooDataServer();
        dataServer.addCompany("fb");
        Thread.sleep(1000);
        assertEquals(dataServer.companyHistory("fb").size() > 0, true);
        dataServer.deleteCompany("fb");
    }

    @Test
    public void testDeleteCompany() throws Exception {

    }

    @Test
    public void testListCompanies() throws Exception {

    }

    @Test
    public void testCompanyHistory() throws Exception {

    }
}