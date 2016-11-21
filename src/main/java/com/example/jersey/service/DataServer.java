package com.example.jersey.service;

import com.example.jersey.model.StockData;

import java.util.List;

/**
 * Created by Shengwei_Wang on 11/19/16.
 */
public interface DataServer {
    void addCompany(String company);
    void deleteCompany(String company);
    List<StockData> listCompanies();
    List<StockData> companyHistory(String company);
    void init();
}
