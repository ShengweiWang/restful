package com.example.jersey.dataFetch;

import com.example.jersey.model.StockData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * A class provides static methods for data fetching
 * from yahoo finance api
 * url = http://finance.yahoo.com/d/quotes.csv?
 *
 * @author Shengwei_Wang
 */
public class DataFetcher {
    /**
     * a method to get stock data from yahoo finance api
     * using StockData model
     *
     * @param symbol : company symbol
     * @return : stock data
     */
    public static StockData getStock(String symbol) {
        String sym = symbol.toUpperCase();
        double price = 0.0;
        double volume = 0;
        double pe = 0.0;
        double eps = 0.0;
        double week52low = 0.0;
        double week52high = 0.0;
        double daylow = 0.0;
        double dayhigh = 0.0;
        double movingav50day = 0.0;
        double marketcap = 0.0;
        String name = "";
        String currency = "";
        double shortRatio = 0.0;
        double open = 0.0;
        double previousClose = 0.0;
        String exchange = "";
        try {

            // Retrieve CSV File
            URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=l1vr2ejkghm3j3nc4s7pox");
            URLConnection connection = yahoo.openConnection();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(is);
            // Parse CSV Into Array
            String line = br.readLine();
            //Only split on commas that aren't in quotes
            String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            // Handle Our Data
            price = DataFetcher.parse(stockinfo[0]);
            volume = DataFetcher.parse(stockinfo[1]);
            pe = DataFetcher.parse(stockinfo[2]);
            eps = DataFetcher.parse(stockinfo[3]);
            week52low = DataFetcher.parse(stockinfo[4]);
            week52high = DataFetcher.parse(stockinfo[5]);
            daylow = DataFetcher.parse(stockinfo[6]);
            dayhigh = DataFetcher.parse(stockinfo[7]);
            movingav50day = DataFetcher.parse(stockinfo[8]);
            marketcap = DataFetcher.parse(stockinfo[9]);
            name = stockinfo[10].replace("\"", "");
            currency = stockinfo[11].replace("\"", "");
            shortRatio = DataFetcher.parse(stockinfo[12]);
            previousClose = DataFetcher.parse(stockinfo[13]);
            open = DataFetcher.parse(stockinfo[14]);
            exchange = stockinfo[15].replace("\"", "");
        } catch (IOException e) {
            Logger log = Logger.getLogger(DataFetcher.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return new StockData(sym, price, volume, pe, eps, week52low, week52high, daylow, dayhigh, movingav50day, marketcap, name, currency, shortRatio, previousClose, open, exchange);

    }

    /**
     * a inner parser, transform String to Double
     *
     * @param x : maybe String formatted double, or "N/A"
     * @return : the parsed number
     */
    private static double parse(String x) {
        double y;
        if (Pattern.matches("N/A", x)) {
            y = 0.00;
        } else {
            y = Double.parseDouble(x);
        }
        return y;
    }
}
