package com.xrc.dsk.connection;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.OPENINGS;

@Slf4j
public class OpeningsRequestBuilder extends RequestHandler<String, String> {
    private final String url;

    public OpeningsRequestBuilder(String method) {
        super(method);
        this.url = OPENINGS;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        return new RequestBuilder(url);
    }

    @Override
    protected Function<Throwable, String> getExceptionallyResult() {
        return e -> "Error while getting openings info";
    }

    @Override
    protected Function<String, String> handleJson(Class<String> responseClass) {
        return json -> jsonConverter.fromJson(json, responseClass);
    }
}
