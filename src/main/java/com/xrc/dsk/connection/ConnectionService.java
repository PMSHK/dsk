package com.xrc.dsk.connection;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ConnectionService {
    private final HttpClient httpClient;
    private HttpRequest request;
    private RequestBuilder requestBuilder;
    public ConnectionService() {
        httpClient = HttpClient.newHttpClient();
    }
    public List<String> getAllMaterials(){
        requestBuilder = new RequestBuilder("http://localhost:8090/protection/all_materials");
        return httpClient.sendAsync(requestBuilder.createRequest("GET"), HttpResponse.BodyHandlers.ofLines())
                .thenApply(HttpResponse::body).thenApply(Stream::toList).join();
    }

}
