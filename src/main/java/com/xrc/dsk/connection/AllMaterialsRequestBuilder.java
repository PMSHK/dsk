package com.xrc.dsk.connection;

import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import javafx.collections.FXCollections;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.ALL_MATERIALS;

@Slf4j
public class AllMaterialsRequestBuilder extends RequestHandler<List<String>, MaterialInfoDataDto> {
    private final String url;

    public AllMaterialsRequestBuilder(String method) {
        super(method);
        this.url = ALL_MATERIALS;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        return new RequestBuilder(url);
    }

    @Override
    protected Function<Throwable, List<String>> getExceptionallyResult() {
        return e -> {
            log.error("Error in {} request to {}", method, url, e);
            return FXCollections.observableArrayList("Error loading materials");
        };
    }

    @Override
    protected Function<String, List<String>> handleJson(Class<MaterialInfoDataDto> responseClass) {
        return json -> jsonConverter.listFromJson(json, responseClass)
                .stream()
                .map(dto -> dto.getName() + " " + dto.getDensity())
                .toList();
    }
}
