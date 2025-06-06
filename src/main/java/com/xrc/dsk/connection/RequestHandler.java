package com.xrc.dsk.connection;

import com.xrc.dsk.converters.JsonConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RequiredArgsConstructor
@Getter
@Slf4j
public abstract class RequestHandler<T, D> {
    protected final String method;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    protected JsonConverter jsonConverter = new JsonConverter();

    public CompletableFuture<T> constructRequest(Class<D> responseClass) {
        return httpClient.sendAsync(
                        constructRequestBuilder().createRequest(method),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(handleJson(responseClass))
                .exceptionally(ex -> {
                    log.error("request failed", ex);
                    return getExceptionallyResult().apply(ex);
                });
    }

    //    abstract String constructUrl();
    protected abstract Function<Throwable, T> getExceptionallyResult();

    protected abstract RequestBuilder constructRequestBuilder();

    protected abstract Function<String, T> handleJson(Class<D> responseClass);
}
