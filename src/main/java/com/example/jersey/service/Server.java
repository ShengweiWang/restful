package com.example.jersey;
/**
 * Created by Shengwei_Wang on 11/19/16.
 */
import com.example.jersey.dataFetch.DataFetcher;
import com.example.jersey.model.StockData;
import com.example.jersey.service.DataServer;
import com.example.jersey.service.YahooDataServer;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;
@Path("")
public class Server extends Application {
    @Path("")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage1() {
        System.out.println("111");
        return "Welcome";
    }
    @Path("/monitor")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        System.out.println("111");
        DataServer dataServer = new YahooDataServer();
        return dataServer.listCompanies().toString();
    }
    @Path("/listcompanies")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listCompanies() {
        DataServer dataServer = new YahooDataServer();
        List<StockData> list = dataServer.listCompanies();
        StringBuffer sb = new StringBuffer();
        for (StockData stockData : list) {
            sb.append(stockData.getSymbol()).append(" ")
                    .append(stockData.getPrice()).append(" ")
                    .append(stockData.getTimeStamp()).append("\n");
        }
        return sb.toString();
    }

    @Path("/history/{company}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String companyHistory(@PathParam("company") String company) {
        DataServer dataServer = new YahooDataServer();
        List<StockData> list = dataServer.companyHistory(company);
        StringBuffer sb = new StringBuffer();
        for (StockData stockData : list) {
            sb.append(stockData.getSymbol()).append(" ")
                    .append(stockData.getPrice()).append(" ")
                    .append(stockData.getTimeStamp()).append("\n");
        }
        try {
            dataServer.addCompany("fb");
        } catch (Exception e) {
            System.out.println(e);
        }
//        dataServer.deleteCompany("aapl");
//        dataServer.addCompany();
        return sb.toString();
    }
}
