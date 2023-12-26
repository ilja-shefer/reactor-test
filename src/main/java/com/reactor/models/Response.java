package com.reactor.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String source;
    private double size;

    public Response(String source, double size) {
        this.source = source;
        this.size = size;
    }
}
