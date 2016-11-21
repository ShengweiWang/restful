package com.example.jersey.model;

import java.sql.Timestamp;

/**
 * Basic data model class, represent the stock information
 * of a specific company at a specific time
 * @author Shengwei_Wang
 * refer : http://www.jarloo.com/yahoo_finance/
 */
public class StockData {
    private String symbol;
    private double price;
    private String timestamp;
    private double volume;
    private double pe;
    private double eps;
    private double week52low;
    private double week52high;
    private double daylow;
    private double dayhigh;
    private double movingav50day;
    private double marketcap;
    private String name;
    private String currency;
    private double shortRatio;
    private double previousClose;
    private double open;
    private String exchange;
    /**
     * Basic Constructor for this program
     * @param symbol : a unique symbol indiate company, like fb for facebook
     * @param timestamp : the timestamp for this data, stores in string
     *                  and represented in java.sql.Timestamp format
     * @param price : stock price
     */
    public StockData(String symbol, String timestamp, double price) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    /**
     * Extended Constuctor for later version
     * @param symbol : company symbol
     * @param price : stock price
     * @param volume : volume
     * @param pe : P/E ratio
     * @param eps : earning per share
     * @param week52low : 52 Week low
     * @param week52high : 52 Week high
     * @param daylow : day low
     * @param dayhigh : day high
     * @param movingav50day : 50 days moving average
     * @param marketcap :market capitalization
     * @param name : company name
     * @param currency : currency
     * @param shortRatio : short ratio
     * @param previousClose : previous close price
     * @param open : open price
     * @param exchange : stock exchange
     */
    public StockData(String symbol, double price, double volume, double pe, double eps, double week52low,
                 double week52high, double daylow, double dayhigh, double movingav50day, double marketcap, String name, String currency, double shortRatio, double previousClose, double open, String exchange) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.volume = volume;
        this.pe = pe;
        this.eps = eps;
        this.week52low = week52low;
        this.week52high = week52high;
        this.daylow = daylow;
        this.dayhigh = dayhigh;
        this.movingav50day = movingav50day;
        this.marketcap = marketcap;
        this.name = name;
        this.currency = currency;
        this.shortRatio = shortRatio;
        this.previousClose = previousClose;
        this.open = open;
        this.exchange = exchange;
    }

    public String getExchange(){
        return this.exchange;
    }

    public double getPreviousClose(){
        return this.previousClose;
    }

    public double getOpen(){
        return this.open;
    }

    public double getShortRatio(){
        return this.shortRatio;
    }

    public String getCurrency(){
        return this.currency;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getTimeStamp() {return timestamp;};

    public double getPrice() {
        return this.price;
    }

    public double getVolume() {
        return this.volume;
    }

    public double getPe() {
        return this.pe;
    }

    public double getEps() {
        return this.eps;
    }

    public double getWeek52low() {
        return this.week52low;
    }

    public double getWeek52high() {
        return this.week52high;
    }

    public double getDaylow() {
        return this.daylow;
    }

    public double getDayhigh() {
        return this.dayhigh;
    }

    public double getMovingav50day() {
        return this.movingav50day;
    }

    public double getMarketcap() {
        return this.marketcap;
    }

    public String getName(){
        return this.name;
    }
}