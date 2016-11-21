package com.example.jersey.database;

import com.example.jersey.model.StockData;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * database connector implemented for mysql, implemented basic CRUD
 * method for data manipulation, based on StockData model
 *
 * @author Shengwei_Wang
 */
public class MysqlDBConnector implements DBConnector {
    Connection con;

    /**
     * Build connection to database, using mysql-connector-java-5.1.6.jar
     * (dependencies in maven), for testing, using
     * localhost/testdb user = "stockmonitor", password = "test"
     */
    public void connect() {

//        System.out.println("trying connect!");
        con = null;
        String url = "jdbc:mysql://localhost/testdb";
        String user = "stockmonitor";
        String password = "test";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //make sure the driver has been loaded
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            Logger log = Logger.getLogger(MysqlDBConnector.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * implemented initialization, delete all data stored
     * used submit method
     */
    public void init() {

        submit("DROP TABLE IF EXISTS stock_price;");
        submit("CREATE TABLE IF NOT EXISTS stock_price(name VARCHAR(100), time TIMESTAMP, price REAL)");

    }

    /**
     * execute non-select sql scripts
     *
     * @param sqlStatement : sql script
     */
    private void submit(String sqlStatement) {
        if (con == null) //test connection once first
            connect();
        try {
            Statement st = con.createStatement();
            st.execute(sqlStatement);
        } catch (SQLException e) {
            Logger log = Logger.getLogger(MysqlDBConnector.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * execute select sql scripts
     *
     * @param sqlStatement : sql scripts
     * @return : a list of stock data stored
     */
    private List<StockData> query(String sqlStatement) {
        List<StockData> ans = new LinkedList<StockData>();
        if (con == null) {
            connect();
        }
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlStatement);
            while (rs.next()) {
                String name = rs.getString(1);
                String timestamp = rs.getString(2);
                double price = rs.getFloat(3);
                ans.add(new StockData(name, timestamp, price));
            }
        } catch (SQLException e) {
            Logger log = Logger.getLogger(MysqlDBConnector.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return ans;
    }


    /**
     * implemented insert method,
     * currently, just store symbol, timestamp, price
     * used submit method
     *
     * @param data : stock data
     */
    public void insert(StockData data) {
        String exec =
                String.format("INSERT INTO stock_price VALUES ('%s', '%s', %f);",
                        data.getSymbol(), data.getTimeStamp(), data.getPrice());
        submit(exec);
    }

    /**
     * Get historical price information stored in the database
     * used query method
     *
     * @param company : symbol of a company, like fb for facebook
     * @return a list of stock data of a company
     */
    public List<StockData> getCompany(String company) {
        String exec =
                String.format("SELECT * FROM stock_price WHERE name = '%s';", company);
        List<StockData> ls = query(exec);
        return ls;
    }

    /**
     * Get all companies stored in the database, with
     * their lasted price
     * used query method
     *
     * @return a list of stock data of all companies stored
     */
    public List<StockData> getCompanyList() {
        String exec = "SELECT a.* FROM stock_price a WHERE time = ( SELECT max(time) FROM stock_price WHERE name = a.name);";
        return query(exec);
    }

    /**
     * Delete company price information
     *
     * @param company : symbol of a company
     */
    public void deleteCompany(String company) {
        String exec =
                String.format("DELETE FROM stock_price WHERE name = '%s';", company);
        submit(exec);
    }
}
