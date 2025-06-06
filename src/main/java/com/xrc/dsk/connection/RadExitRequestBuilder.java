package com.xrc.dsk.connection;

import com.xrc.dsk.dto.KermaParamDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.RAD_EXIT;

@Slf4j
public class RadExitRequestBuilder extends RequestHandler<Double, Double> {
    private final String url;
    private final double voltage;

    public RadExitRequestBuilder(String method, double voltage) {
        super(method);
        this.url = RAD_EXIT;
        this.voltage = voltage;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        KermaParamDto kParamDto = new KermaParamDto(voltage);
        String body = jsonConverter.toJson(kParamDto);
        return new RequestBuilder(url, body);
    }

    @Override
    protected Function<Throwable, Double> getExceptionallyResult() {
        return e -> 0d;
    }

    @Override
    protected Function<String, Double> handleJson(Class<Double> responseClass) {
        return json -> jsonConverter.fromJson(json, responseClass);
    }
}
