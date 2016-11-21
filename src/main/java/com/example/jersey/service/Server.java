package com.example.jersey;
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

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Server extends Application {
    @Path("")
    @GET
    public Viewable startPage() {
        DataServer dataServer = new YahooDataServer();
        dataServer.init();
        return new Viewable("/index", null);
    }
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

    @Path("/listcompanies")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listCompanies() {
        String json = "";
        try {
            System.out.println("in list");
            DataServer dataServer = new YahooDataServer();
            List<StockData> list = dataServer.listCompanies();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(list);
        } catch (Exception e){
            System.err.println(e);
        }
        return json;
    }

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
        } catch (Exception e){
            System.err.println(e);
        }
        return json;
//        StringBuffer sb = new StringBuffer();
//        for (StockData stockData : list) {
//            sb.append(stockData.getSymbol()).append(" ")
//                    .append(stockData.getPrice()).append(" ")
//                    .append(stockData.getTimeStamp()).append("\n");
//        }
//        Map<String, String > hashmap = new HashMap<String, String>();
//        hashmap.put("list",sb.toString());

    }


    @Path("/add")
    @POST
    public Viewable addCompany(@FormParam("company") String para1) {
        System.out.println("in add");
        DataServer dataServer = new YahooDataServer();
        try {
            dataServer.addCompany(para1);
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("out add");
        return new Viewable("/index", null);
    }

    @Path("/delete")
    @POST
    public Viewable deleteCompany(@Context UriInfo uriInfo, @FormParam("company") String para1) {
        System.out.println("in delete");
        DataServer dataServer = new YahooDataServer();
        try {
            dataServer.deleteCompany(para1);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("exix" + para1);
        }
        return new Viewable("/index", null);
    }
}
