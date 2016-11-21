package com.example.jersey.dataFetch;

import com.example.jersey.database.DBConnector;

/**
 * Created by Shengwei_Wang on 11/18/16.
 */

public class Engine extends Thread{
    String company;
    volatile boolean running;
    long interval;
    DBConnector dbConnector;
    public Engine(String company, long interval, DBConnector dbConnector) {
        this.company = company;
        this.interval = interval;
        this.dbConnector = dbConnector;
        this.running = true;
    }
    public void run() {
        while (running) {
            try {
                dbConnector.insert(DataFetcher.getStock(company));
                sleep(interval);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("ready to clean up");
        dbConnector.deleteCompany(company);
    }

    public void setRunning(boolean b) {
        running = b;
    }



}
