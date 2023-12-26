package com.reactor.clients;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class BingClient extends BaseSearchClient {

    private static final Logger LOGGER = Logger.getLogger(BingClient.class.getName());
    private static final String BASE_URL = "https://www.bing.com";
    private static final String HEADER = "User-Agent";
    private static final String HEADER_VALUES = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/58.0.3029.110 Safari/537.36";

    public BingClient() {
        super(BASE_URL,HEADER, HEADER_VALUES, LOGGER);
    }

    public Mono<String> getDataFromBing(String query) {
        String urlBing = "/search?q=" + query + "&num=20";
        LOGGER.info("Sending request..." + BASE_URL + urlBing);

        return fetchData(urlBing);
    }
}