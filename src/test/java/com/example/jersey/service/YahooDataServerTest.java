package com.example.jersey.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shengwei_Wang on 11/19/16.
 */
public class YahooDataServerTest {
    @Test
    public void testDeleteCompany() throws Exception {
        DataServer dataServer = new YahooDataServer();
        dataServer.init();
        dataServer.addCompany("fb");
        Thread.sleep(2000);
        dataServer.deleteCompany("fb");
        assertEquals(true, dataServer.companyHistory("fb").size() == 0);
        dataServer.init();
    }

    @Test
    public void testAddCompany() throws Exception {
        DataServer dataServer = new YahooDataServer();
        dataServer.init();
        dataServer.addCompany("fb");
        Thread.sleep(2000);
        assertEquals(true, dataServer.companyHistory("fb").size() > 0);
        dataServer.init();
    }


    @Test
    public void testListCompanies() throws Exception {
        DataServer dataServer = new YahooDataServer();
        dataServer.init();
        dataServer.addCompany("fb");
        dataServer.addCompany("aapl");
        Thread.sleep(2000);
        assertEquals(true, dataServer.listCompanies().size() > 0);
        dataServer.init();
    }

    @Test
    public void testCompanyHistory() throws Exception {
        DataServer dataServer = new YahooDataServer();
        dataServer.init();
        dataServer.addCompany("fb");
        dataServer.addCompany("aapl");
        Thread.sleep(2000);
        dataServer.deleteCompany("aapl");
        assertEquals(true, dataServer.companyHistory("fb").size() > 0);
        assertEquals(true, dataServer.companyHistory("aapl").size() == 0);
        dataServer.init();
    }
}