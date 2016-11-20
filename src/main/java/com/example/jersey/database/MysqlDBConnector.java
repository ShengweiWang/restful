package com.example.jersey.database;
import com.example.jersey.model.StockData;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Shengwei_Wang on 11/18/16.
 */
public class MysqlDBConnector implements DBConnector { //first version : single thread

    Connection con;
    public void connect() {
        con = null;
        String url = "jdbc:mysql://localhost/testdb";
        String user = "stockmonitor";
        String password = "test";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            Logger log = Logger.getLogger(MysqlDBConnector.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }
    public void init() {

        submit("DROP TABLE IF EXISTS stock_price;");
        submit("CREATE TABLE IF NOT EXISTS stock_price(name VARCHAR(100), time TIMESTAMP, price REAL)");

    }
    void submit (String sqlStatement) {
        if (con == null)
            connect();
        try {
            Statement st = con.createStatement();
//            st.executeQuery(sqlStatement);
            st.execute(sqlStatement);
        } catch (SQLException e) {
            Logger log = Logger.getLogger(MysqlDBConnector.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    List<StockData> query (String sqlStatement) {
        List<StockData> ans = new LinkedList<StockData>();
        if (con == null)
            connect();
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sqlStatement);
            while (rs.next()) {
                String name = rs.getString(1);
                String timestamp = rs.getString(2);
                double price = rs.getFloat(3);
                ans.add(new StockData(name, timestamp, price));
            }
//            st.execute(sqlStatement);
        } catch (SQLException e) {
            Logger log = Logger.getLogger(MysqlDBConnector.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return ans;
    }



    public void insert(StockData data) {
        String insertion = "INSERT INTO stock_price\n" +
                "VALUES ('" + data.getSymbol() + "', '" + data.getTimeStamp() + "'," + data.getPrice() + ");";
        submit(insertion);
    }
    public List<StockData> getCompany(String company){
        String selection = "SELECT * FROM stock_price\n" +
                "WHERE name ='" + company + "';";
//        System.out.println(selection);
        List<StockData> ls = query(selection);
        return ls;
    }
    public List<StockData> getCompanyList(){
        String selection = "SELECT a.* FROM stock_price a WHERE time = ( SELECT max(time) FROM stock_price WHERE name = a.name);";
        return query(selection);
    }
    public void deleteCompany(String company){
        String deletion = "DELETE FROM stock_price\n" +
                "WHERE name ='" + company + "';";
        submit(deletion);
    }
}
