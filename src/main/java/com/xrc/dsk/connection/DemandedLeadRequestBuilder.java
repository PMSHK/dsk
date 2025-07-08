package com.xrc.dsk.connection;

import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.DEMAND_LEAD_EQUIVALENT;

@Slf4j
public class DemandedLeadRequestBuilder extends RequestHandler<ProtectionDataDto, ProtectionDataDto> {
    private final String url;
    private final KParamDto dto;

    public DemandedLeadRequestBuilder(String method, KParamDto dto) {
        super(method);
        this.url = DEMAND_LEAD_EQUIVALENT;
        this.dto = dto;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        String json = jsonConverter.toJson(dto);
        return new RequestBuilder(url, json);
    }

    @Override
    protected Function<Throwable, ProtectionDataDto> getExceptionallyResult() {
        return e -> new ProtectionDataDto();
    }

    @Override
    protected Function<String, ProtectionDataDto> handleJson(Class<ProtectionDataDto> responseClass) {
        return json -> jsonConverter.fromJson(json, responseClass);
    }
}
