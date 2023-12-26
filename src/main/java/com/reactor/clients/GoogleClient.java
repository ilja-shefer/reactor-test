package com.reactor.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Component
public class GoogleClient extends BaseSearchClient  {
    public static final Logger LOGGER = Logger.getLogger(GoogleClient.class.getName());
    public static final String BASE_URL = "https://www.google.com";

    public static final String HEADER = "User-Agent";
    public static final String HEADER_VALUES = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";

    public GoogleClient() {
        super(BASE_URL,HEADER, HEADER_VALUES, LOGGER);
    }


    public Mono<String> getDataFromGoogle(String query) {

        String urlGoogle = "/search?q=" + query + "&num=20";
        LOGGER.info("Sending request..." + BASE_URL + urlGoogle);

        return fetchData(urlGoogle);
    }

}
