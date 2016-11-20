package com.example.jersey.dataFetch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shengwei_Wang on 11/19/16.
 */
public class DataFetcherTest {

    @Test
    public void testGetStock() throws Exception {
        assertNotEquals(DataFetcher.getStock("fb"), null);
        assertNotEquals(DataFetcher.getStock("aapl"), null);
        assertEquals(DataFetcher.getStock("fb").getSymbol(), "FB");
    }
}