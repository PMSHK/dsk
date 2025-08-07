package com.xrc.dsk.connection;

import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.ADD_MATERIAL;
import static com.xrc.dsk.settings.ApiEndpoints.DEL_MATERIAL;
import static com.xrc.dsk.settings.ApiEndpoints.UPDATE_MATERIAL;

@Slf4j
public class MaterialsManagerRequestBuilder<T> extends RequestHandler<ServerResponse, String> {
    private final String url;
    private final T body;

    public MaterialsManagerRequestBuilder(String method, T body) {
        super(method);
        this.body = body;
        switch (method) {
            case "POST" -> this.url = ADD_MATERIAL;
            case "DELETE" -> this.url = DEL_MATERIAL;
            case "PUT" -> this.url = UPDATE_MATERIAL;
            default -> throw new IllegalArgumentException("Unsupported method: " + method);
        }
    }

    public MaterialsManagerRequestBuilder(String method, T body, String materialName, float density) {
        super(method);
        this.body = body;
        switch (method) {
            case "POST" -> this.url = ADD_MATERIAL;
            case "DELETE" -> this.url = DEL_MATERIAL;
            case "PUT" -> {
                String categoryEncoded = URLEncoder.encode(materialName, StandardCharsets.UTF_8)
                        .replace("+", "%20");
                this.url = String.format("%s?materialName=%s&materialDensity=%f",
                        UPDATE_MATERIAL, categoryEncoded, density);

//                this.url = UPDATE_MATERIAL + categoryEncoded;
                log.info("url has configured {}", url);
            }
            default -> throw new IllegalArgumentException("Unsupported method: " + method);
        }
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        return new RequestBuilder(url, jsonConverter.toJson(body));
    }

    @Override
    protected Function<Throwable, ServerResponse> getExceptionallyResult() {
        return e -> {
            log.error("Error in {} request to {}", method, url, e);
            return new ServerResponse(500, "Illegal request body");
        };
    }

    @Override
    protected Function<String, ServerResponse> handleJson(Class<String> responseClass) {
        return json -> {
            if (json == null || json.isEmpty()) {
                return new ServerResponse(500, "Empty json");
            }
            return new ServerResponse(200, json);
        };
    }
}
