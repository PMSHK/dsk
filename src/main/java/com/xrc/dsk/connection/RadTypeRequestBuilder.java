package com.xrc.dsk.connection;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.RAD_TYPES;

@Slf4j
public class RadTypeRequestBuilder extends RequestHandler<List<String>, String> {
    private final String url;

    public RadTypeRequestBuilder(String method) {
        super(method);
        this.url = RAD_TYPES;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        return new RequestBuilder(url, "{}");
    }

    @Override
    protected Function<Throwable, List<String>> getExceptionallyResult() {
        return e -> {
            log.error("Error in {} request to {}", method, url, e);
            return List.of("Error loading materials");
        };
    }

    @Override
    protected Function<String, List<String>> handleJson(Class<String> responseClass) {
        return json -> jsonConverter.listFromJson(json, responseClass);
    }
}
