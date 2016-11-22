package com.example.jersey.dataFetch;

import com.example.jersey.database.DBConnector;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * a data engine, keep gathering stock data using DataFetcher
 * each company will have an engine which is a single thread
 * to start: using start() to run
 * to shutdown : first setRunning(false), then join();
 *
 * @author Shengwei_Wang
 */
public class Engine extends Thread {
    String company;             //company symbol
    volatile boolean running;   //running status, true for running
    long interval;              //interval time
    DBConnector dbConnector;    //databse connector

    /**
     * Constructor
     *
     * @param company     : company symbol
     * @param interval    : interval time
     * @param dbConnector : database connector
     */
    public Engine(String company, long interval, DBConnector dbConnector) {
        this.company = company;
        this.interval = interval;
        this.dbConnector = dbConnector;
        this.running = true;
    }

    /**
     * override run() method in Thread
     * controled by running status variable
     * once about to finish, it will cleanup data stored
     */
    @Override
    public void run() {
        while (running) {
            try {
                dbConnector.insert(DataFetcher.getStock(company));
                try {
                    sleep(interval);
                } catch (InterruptedException ie) {

                }
            } catch (Exception e) {
                Logger log = Logger.getLogger(Engine.class.getName());
                log.log(Level.SEVERE, e.toString(), e);
            }
        }
        dbConnector.deleteCompany(company);
    }

    /**
     * Running state setting
     *
     * @param b : true for running, false for shutdown
     */
    public void setRunning(boolean b) {
        running = b;
    }


}
