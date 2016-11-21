package com.example.jersey.service;


import com.example.jersey.dataFetch.Engine;
import com.example.jersey.database.DBConnector;
import com.example.jersey.database.MysqlDBConnector;
import com.example.jersey.model.StockData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shengwei_Wang on 11/19/16.
 */
public class YahooDataServer implements DataServer {
    final static long interval = 10000;
    final static DBConnector dbConnector = new MysqlDBConnector();
    static Map<String, Engine> map = new HashMap<String, Engine>();
//    public static DataServer ServerFactory() {
//        return new YahooDataServer();
//    }
    public void addCompany(String company) {
        if (!map.containsKey(company)) {
            map.put(company, new Engine(company, interval, dbConnector));
        }
        Engine engine = map.get(company);
        engine.setRunning(true);
        engine.start();
    }
    public void deleteCompany(String company) {
        try {
            if (!map.containsKey(company))
                return;
            Engine engine = map.get(company);
            map.remove(company);
            engine.setRunning(false);
            engine.join();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public List<StockData> listCompanies() {
        List<StockData> companyList = dbConnector.getCompanyList();
        return companyList;
    }

    public List<StockData> companyHistory(String company) {
        List<StockData> companyHistory = dbConnector.getCompany(company);
        return companyHistory;
    }

    public void cleanup() {
        for (String key : map.keySet()) {
            deleteCompany(key);
        }
        dbConnector.init();
    }
}
