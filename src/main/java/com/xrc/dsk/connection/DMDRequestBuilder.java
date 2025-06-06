package com.xrc.dsk.connection;

import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.ROOM_CATEGORY;

@Slf4j
public class DMDRequestBuilder extends RequestHandler<String, String> {
    private final String url;
    private final String category;

    public DMDRequestBuilder(String method, String category) {
        super(method);
        this.url = ROOM_CATEGORY;
        this.category = category;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        String categoryEncoded = URLEncoder.encode(category, StandardCharsets.UTF_8);
        categoryEncoded = categoryEncoded.replace("+", "%20");
        String encodedUrl = url + categoryEncoded;
        return new RequestBuilder(encodedUrl);
    }

    @Override
    protected Function<Throwable, String> getExceptionallyResult() {
        return e -> "Error while getting DMD info";
    }

    @Override
    protected Function<String, String> handleJson(Class<String> responseClass) {
        return json -> jsonConverter.fromJson(json, responseClass);
    }
}
