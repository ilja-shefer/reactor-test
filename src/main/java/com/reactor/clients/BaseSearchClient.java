package com.reactor.clients;

import com.reactor.exeptions.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.logging.Logger;

public class BaseSearchClient {
    protected Logger logger;
    protected WebClient webClient;

    public BaseSearchClient(String baseUrl,String header, String headerValues, Logger logger) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(header, headerValues)
                .build();
        this.logger = logger;
    }

    protected Mono<String> fetchData(String queryPath) {
        return webClient.get()
                .uri(queryPath)
                .retrieve()
                .bodyToMono(String.class);
    }
}
