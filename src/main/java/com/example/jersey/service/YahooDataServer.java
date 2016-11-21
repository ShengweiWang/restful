package com.example.jersey.service;


import com.example.jersey.dataFetch.Engine;
import com.example.jersey.database.DBConnector;
import com.example.jersey.database.MysqlDBConnector;
import com.example.jersey.model.StockData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation for data server
 * provide basic CRUD operation api
 *
 * @author Shengwei_Wang
 */
public class YahooDataServer implements DataServer {

    final static long interval = 10000; // interval time default setting, should be 5 * 60 * 1000 for testing just set it to 10000;
    final static DBConnector dbConnector = new MysqlDBConnector();
    static Map<String, Engine> map = new HashMap<String, Engine>(); // a map stores <company, Engine>

    /**
     * Start company data engine service
     *
     * @param company : company symbol
     */
    public void addCompany(String company) {
        try {
//            System.out.println("int add companey");
            YahooDataServer.class.newInstance(); //load the server
            if (!map.containsKey(company)) {
                map.put(company, new Engine(company, interval, dbConnector));
            }
//            System.out.println("YahooDataServer loaded");
            Engine engine = map.get(company);
            engine.setRunning(true);            //set running status
            engine.start();                     //start running
        } catch (Exception e) {
            Logger log = Logger.getLogger(YahooDataServer.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * Shutdown company data engine service
     *
     * @param company : company symbol
     */
    public void deleteCompany(String company) {
        try {
            YahooDataServer.class.newInstance();
            if (!map.containsKey(company))
                return;
            Engine engine = map.get(company);
            map.remove(company);
            engine.setRunning(false);
            engine.join();
        } catch (Exception e) {
            Logger log = Logger.getLogger(YahooDataServer.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * Retrieve information of all companies with
     * their lasted price
     *
     * @return : a list of stock data
     */
    public List<StockData> listCompanies() {
        List<StockData> companyList = dbConnector.getCompanyList();
        return companyList;
    }

    /**
     * Retrieve information of a single company
     * with it's historical data
     *
     * @param company : company symbol
     * @return : a list of stock data
     */
    public List<StockData> companyHistory(String company) {
        List<StockData> companyHistory = dbConnector.getCompany(company);
        return companyHistory;
    }

    /**
     * shutdown all the data service
     * and clean up the database
     */
    public void init() {
        try {
            YahooDataServer.class.newInstance();
            for (String key : map.keySet()) {
                deleteCompany(key);
            }
            dbConnector.init();
        } catch (Exception e) {
            Logger log = Logger.getLogger(YahooDataServer.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }
}
