package com.xrc.dsk.connection;

import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.OPENINGS;

@Slf4j
public class OpeningsRequestBuilder extends RequestHandler<String, String> {
    private final String url;

    public OpeningsRequestBuilder(String method) {
        super(method);
        this.url = OPENINGS;
    }

    public OpeningsRequestBuilder(String method, String name, Double value, Double precision) {
        super(method);
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        this.url = String.format("%s?name=%s&lead=%s&step=%s", OPENINGS, encodedName, value, precision);
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
        return json -> {
            log.debug("Raw response: {}", json);
            return json;
        };
    }
}
