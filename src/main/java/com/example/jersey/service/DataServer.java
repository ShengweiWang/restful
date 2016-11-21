package com.example.jersey.service;

import com.example.jersey.model.StockData;

import java.util.List;

/**
 * An interface for data server
 * provide basic CRUD operation api
 *
 * @author Shengwei_Wang
 */
public interface DataServer {
    /**
     * Start company data engine service
     *
     * @param company : company symbol
     */
    void addCompany(String company);

    /**
     * Shutdown company data engine service
     *
     * @param company : company symbol
     */
    void deleteCompany(String company);

    /**
     * Retrieve information of all companies with
     * their lasted price
     *
     * @return : a list of stock data
     */
    List<StockData> listCompanies();

    /**
     * Retrieve information of a single company
     * with it's historical data
     *
     * @param company : company symbol
     * @return : a list of stock data
     */
    List<StockData> companyHistory(String company);

    /**
     * shutdown all the data service
     * and clean up the database
     */
    void init();
}
