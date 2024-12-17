package com.xrc.dsk.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private String url;
    private String method;
    private String body;
    private Map<String, HttpRequest> methods = new HashMap<>();

    public RequestBuilder(String url) {
        this.url = url;
        methods.put("GET", getMethod());
        methods.put("POST", postMethod());
        methods.put("PUT", putMethod());
        methods.put("DELETE", deleteMethod());
    }

    public RequestBuilder(String url, String body) {
        this.url = url;
        this.body = body;
        methods.put("GET", getMethod());
        methods.put("POST", postMethod());
        methods.put("PUT", putMethod());
        methods.put("DELETE", deleteMethod());
    }

    public HttpRequest createRequest(String method) {
        return methods.get(method);
    }

    private HttpRequest getMethod() {
        return HttpRequest.newBuilder()
                .uri(getURI())
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
    }

    private HttpRequest postMethod() {
        if (body != null) {
            return HttpRequest.newBuilder()
                    .uri(getURI())
                    .timeout(Duration.ofSeconds(5))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
        }
        return HttpRequest.newBuilder()
                .uri(getURI())
                .timeout(Duration.ofSeconds(5))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private HttpRequest putMethod() {
        if (body != null) {
            return HttpRequest.newBuilder()
                    .uri(getURI())
                    .timeout(Duration.ofSeconds(5))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();
        }
        return HttpRequest.newBuilder()
                .uri(getURI())
                .timeout(Duration.ofSeconds(5))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private HttpRequest deleteMethod() {
        return HttpRequest.newBuilder()
                .uri(getURI())
                .timeout(Duration.ofSeconds(5))
                .DELETE()
                .build();
    }

    private URI getURI() {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
