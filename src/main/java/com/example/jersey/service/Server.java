package com.example.jersey.service;
/**
 * Created by Shengwei_Wang on 11/19/16.
 */

import com.example.jersey.dataFetch.DataFetcher;
import com.example.jersey.model.StockData;
import com.example.jersey.service.DataServer;
import com.example.jersey.service.YahooDataServer;
import org.apache.http.client.utils.URIBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Core RESTful api service provider application,
 * handles all the request, and communicate with
 * DataServer, and sent json formatted message to
 * UI
 *
 * @author Shengwei_Wang
 */
@Path("/")
public class Server extends Application {
    /**
     * starting the page, initialized the data server
     * run every time the server starts
     *
     * @return : viewable response, and redirect to
     * index.jsp
     */
    @Path("")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable startPage() {
        DataServer dataServer = new YahooDataServer();
        dataServer.init();
        return new Viewable("/index", null);
    }

    /**
     * handle request for list all the companies
     *
     * @return : a json formatted string, a list of stock data
     * for all companies with their latest price
     */
    @Path("/listcompanies")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listCompanies() {
        String json = "";
        try {
//            System.out.println("in list");
            DataServer dataServer = new YahooDataServer();
            List<StockData> list = dataServer.listCompanies();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(list);
        } catch (Exception e) {
            Logger log = Logger.getLogger(Server.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return json;
    }

    /**
     * handle request for list historical price of a certain company
     *
     * @param company : company symbol, get from request
     * @return : a json formatted string, a list of stock data
     */
    @Path("/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String companyHistory(@QueryParam("company") String company) {
        String json = "";
        try {
            DataServer dataServer = new YahooDataServer();
            List<StockData> list = dataServer.companyHistory(company);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(list);
        } catch (Exception e) {
            Logger log = Logger.getLogger(Server.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return json;


    }

    /**
     * handle request for adding company
     *
     * @param company : company name
     * @return : viewable
     */
    @Path("/add")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Viewable addCompany(@FormParam("company") String company) {
//        System.out.println("in add");
        DataServer dataServer = new YahooDataServer();
        try {
            dataServer.addCompany(company);
        } catch (Exception e) {
            Logger log = Logger.getLogger(Server.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
//        System.out.println("out add");
        return new Viewable("/index", null);
    }


    /**
     * handle request for deleting company
     *
     * @param company : company name
     * @return : viewable to index
     */
    @Path("/delete")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Viewable deleteCompany(@FormParam("company") String company) {
//        System.out.println("in delete");
        DataServer dataServer = new YahooDataServer();
        try {
            dataServer.deleteCompany(company);
        } catch (Exception e) {
            Logger log = Logger.getLogger(Server.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return new Viewable("/index", null);
    }
}

//Debug
//    @Path("/listcompanies")
//    @GET
//    public Viewable listCompanies() {
//        System.out.println("in list");
//        DataServer dataServer = new YahooDataServer();
//        List<StockData> list = dataServer.listCompanies();
//        StringBuffer sb = new StringBuffer();
//        for (StockData stockData : list) {
//            sb.append(stockData.getSymbol()).append(" ")
//                    .append(stockData.getPrice()).append(" ")
//                    .append(stockData.getTimeStamp()).append("\n");
//        }
//        Map<String, String > hashmap = new HashMap<String, String>();
//        hashmap.put("list",sb.toString());
//        return new Viewable("/index", hashmap);
//    }

//        StringBuffer sb = new StringBuffer();
//        for (StockData stockData : list) {
//            sb.append(stockData.getSymbol()).append(" ")
//                    .append(stockData.getPrice()).append(" ")
//                    .append(stockData.getTimeStamp()).append("\n");
//        }
//        Map<String, String > hashmap = new HashMap<String, String>();
//        hashmap.put("list",sb.toString());