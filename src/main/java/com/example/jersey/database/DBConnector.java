package com.example.jersey.database;

import com.example.jersey.model.StockData;

import java.util.List;

/**
 * database connector interface, implemented basic CRUD
 * method for data manipulation, based on StockData model
 *
 * @author Shengwei_Wang
 */
public interface DBConnector {
    /**
     * database connector
     */
    void connect();

    /**
     * initialization, delete all the data inside the database
     */
    void init();

    /**
     * Insert data into the database
     *
     * @param data : stock data
     */
    void insert(StockData data);

    /**
     * Get historical price information stored in the database
     *
     * @param company : symbol of a company, like fb for facebook
     * @return a list of stock data of a company
     */
    List<StockData> getCompany(String company);

    /**
     * Get all companies stored in the database, with
     * their lasted price
     *
     * @return a list of stock data of all companies stored
     */
    List<StockData> getCompanyList();

    /**
     * Delete company price information
     *
     * @param company : symbol of a company
     */
    void deleteCompany(String company);
}
