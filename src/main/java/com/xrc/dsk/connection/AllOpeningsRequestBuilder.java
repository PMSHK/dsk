package com.xrc.dsk.connection;

import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import javafx.collections.FXCollections;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.ALL_OPENINGS;

@Slf4j
public class AllOpeningsRequestBuilder extends RequestHandler<List<String>, OpeningsDataDto> {
    private final String url;

    public AllOpeningsRequestBuilder(String method) {
        super(method);
        this.url = ALL_OPENINGS;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        return new RequestBuilder(url);
    }

    @Override
    protected Function<Throwable, List<String>> getExceptionallyResult() {
        return e -> {
            log.error("Error in {} request to {}", method, url, e);
            return FXCollections.observableArrayList("Error loading openings");
        };
    }

    @Override
    protected Function<String, List<String>> handleJson(Class<OpeningsDataDto> responseClass) {
        return json -> jsonConverter.listFromJson(json, responseClass)
                .stream()
                .map(OpeningsDataDto::getName)
                .toList();
    }
}
