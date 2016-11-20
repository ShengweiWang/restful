package com.example.jersey.database;
import com.example.jersey.model.StockData;
import java.util.List;

/**
 * Created by Shengwei_Wang on 11/18/16.
 */
public interface DBConnector {
    void connect();
    void init();
    void insert(StockData data);
    List<StockData> getCompany(String company);
    List<StockData> getCompanyList();
    void deleteCompany(String company);
}
