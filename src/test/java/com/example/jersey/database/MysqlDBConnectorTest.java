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
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("fb"));
        Thread.sleep(1001);
        mysqlDBConnector.insert(DataFetcher.getStock("aapl"));

        assertEquals(3, mysqlDBConnector.getCompany("fb").size());
        assertEquals(1, mysqlDBConnector.getCompany("aapl").size());
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

        assertEquals(2, mysqlDBConnector.getCompanyList().size());
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
        assertEquals(1, mysqlDBConnector.getCompanyList().size());
        mysqlDBConnector.deleteCompany("aapl");
        assertEquals(0, mysqlDBConnector.getCompanyList().size());

    }
}