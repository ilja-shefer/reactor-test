package com.reactor.processors;

import com.reactor.models.Response;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class ResponseProcessor {
    public Mono<Response> processResponses(Mono<String> googleResult, Mono<String> bingResult) {
        return Mono.zip(googleResult, bingResult, (googleResponse, bingResponse) -> {
            double googleSize = calculateResponseSizeInKB(googleResponse);
            double bingSize = calculateResponseSizeInKB(bingResponse);

            if(googleSize > bingSize) {
                return new Response("Bing", bingSize);
            } else {
                return new Response("Google", googleSize);
            }
        });
    }

    public double calculateResponseSizeInKB(String response) {
        if(response == null || response.isEmpty()) return 0.0;

        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        return (double) responseBytes.length / 1024;
    }
}
