package com.example.jersey.database;

import static org.junit.Assert.*;

import com.example.jersey.dataFetch.DataFetcher;
import com.example.jersey.model.StockData;
import org.junit.Test;

/**
 * Created by Shengwei_Wang on 11/18/16.
 */
public class MysqlDBConnectorTest {

    @Test
    public void testInit() throws Exception {
        MysqlDBConnector mysqlDBConnector = new MysqlDBConnector();
        mysqlDBConnector.connect();
        mysqlDBConnector.init();

    }

    @Test
    public void testInsert() throws Exception {
        MysqlDBConnector mysqlDBConnector = new MysqlDBConnector();
        StockData data = DataFetcher.getStock("fb");
        mysqlDBConnector.insert(data);

    }

    @Test
    public void testGetCompany() throws Exception {
        MysqlDBConnector mysqlDBConnector = new MysqlDBConnector();
        mysqlDBConnector.init();
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        mysqlDBConnector.insert(DataFetcher.getStock("aapl"));

        assertEquals(mysqlDBConnector.getCompany("fb").size(), 3);
        assertEquals(mysqlDBConnector.getCompany("aapl").size(), 1);
    }

    @Test
    public void testGetCompanyList() throws Exception {
        MysqlDBConnector mysqlDBConnector = new MysqlDBConnector();
        mysqlDBConnector.init();
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        mysqlDBConnector.insert(DataFetcher.getStock("aapl"));

        assertEquals(mysqlDBConnector.getCompanyList().size(), 2);
    }

    @Test
    public void testDeleteCompany() throws Exception {
        MysqlDBConnector mysqlDBConnector = new MysqlDBConnector();
        mysqlDBConnector.init();
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        mysqlDBConnector.insert(DataFetcher.getStock("aapl"));
        mysqlDBConnector.deleteCompany("fb");
        assertEquals(mysqlDBConnector.getCompanyList().size(), 1);
        mysqlDBConnector.deleteCompany("aapl");
        assertEquals(mysqlDBConnector.getCompanyList().size(), 0);

    }

    @Test
    public void testConnect() throws Exception {

    }

    @Test
    public void testSubmit() throws Exception {

    }

    @Test
    public void testQuery() throws Exception {

    }
}