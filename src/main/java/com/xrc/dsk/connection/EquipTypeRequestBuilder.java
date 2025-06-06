package com.xrc.dsk.connection;

import com.xrc.dsk.dto.RadiationTypeDto;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.EQUIP_TYPE;

@Slf4j
public class EquipTypeRequestBuilder extends RequestHandler<RadiationTypeDto, RadiationTypeDto> {
    private final String url;
    private final String typeName;
    private final String type;

    public EquipTypeRequestBuilder(String method, String typeName, String type) {
        super(method);
        this.url = EQUIP_TYPE;
        this.typeName = typeName;
        this.type = type;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        String encodedTypeName = URLEncoder.encode(typeName, StandardCharsets.UTF_8);
        encodedTypeName = encodedTypeName.replace("+", "%20");
        String encodedType = URLEncoder.encode(type, StandardCharsets.UTF_8);
        String encodedUrl = url + encodedTypeName + "?type=" + encodedType;
        return new RequestBuilder(encodedUrl);
    }

    @Override
    protected Function<Throwable, RadiationTypeDto> getExceptionallyResult() {
        return e -> new RadiationTypeDto();
    }

    @Override
    protected Function<String, RadiationTypeDto> handleJson(Class<RadiationTypeDto> responseClass) {
        return json -> jsonConverter.fromJson(json, responseClass);
    }
}
