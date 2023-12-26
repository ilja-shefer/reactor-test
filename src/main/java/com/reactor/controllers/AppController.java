package com.reactor.controllers;

import com.reactor.clients.BingClient;
import com.reactor.clients.GoogleClient;
import com.reactor.exeptions.ClientException;
import com.reactor.models.Response;
import com.reactor.processors.ResponseProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AppController {
    private final BingClient bingClient;
    private final GoogleClient googleClient;
    private final ResponseProcessor responseProcessor;

    public AppController(BingClient bingClient,
                         GoogleClient googleClient,
                         ResponseProcessor responseProcessor) {
        this.bingClient = bingClient;
        this.googleClient = googleClient;
        this.responseProcessor = responseProcessor;
    }

    @GetMapping("/fetch")
    public Mono<Response> fetchFromBingAndGoogle(@RequestParam String query) {

        Mono<String> bingResult = bingClient.getDataFromBing(query)
                .onErrorResume(throwable -> Mono.error(new ClientException("Error to get data form Bing.com")));
        Mono<String> googleResult = googleClient.getDataFromGoogle(query)
                .onErrorResume(throwable -> Mono.error(new ClientException("Error to get data from Google.com")));


        return responseProcessor.processResponses(googleResult, bingResult);

    }
}
