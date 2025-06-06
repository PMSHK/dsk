package com.xrc.dsk.connection;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.DIRECTION_COEFFICIENT;

@Slf4j
public class DirectionCoefficientsRequestBuilder extends RequestHandler<List<Double>, Double> {
    private final String url;

    public DirectionCoefficientsRequestBuilder(String method) {
        super(method);
        this.url = DIRECTION_COEFFICIENT;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        return new RequestBuilder(url);
    }

    @Override
    protected Function<Throwable, List<Double>> getExceptionallyResult() {
        return e -> {
            log.error("Error in {} request to {}", method, url, e);
            return List.of(0d);
        };
    }

    @Override
    protected Function<String, List<Double>> handleJson(Class<Double> responseClass) {
        return json -> jsonConverter.listFromJson(json, responseClass);
    }
}
