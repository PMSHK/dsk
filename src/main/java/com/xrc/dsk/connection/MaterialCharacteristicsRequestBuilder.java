package com.xrc.dsk.connection;

import com.xrc.dsk.dto.KermaParamDto;
import com.xrc.dsk.dto.MaterialDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static com.xrc.dsk.settings.ApiEndpoints.MAT_LEAD_EQUIVALENT;
import static com.xrc.dsk.settings.ApiEndpoints.MAT_THICKNESS_EQUIVALENT;
import static com.xrc.dsk.settings.AppParameters.MCS_HOST;
import static com.xrc.dsk.settings.AppParameters.MCS_PORT;

@Slf4j
public class MaterialCharacteristicsRequestBuilder extends RequestHandler<Double, Double> {
    private final String url;
    private final String name;
    private final double voltage;
    private final double density;
    private final double thickness;
    private final double leadEquivalent;

    public MaterialCharacteristicsRequestBuilder(String method,
                                                 String name,
                                                 double density,
                                                 double voltage,
                                                 double thickness,
                                                 double leadEquivalent) {
        super(method);
        this.url = MAT_THICKNESS_EQUIVALENT;
        this.name = name;
        this.voltage = voltage;
        this.density = density;
        this.thickness = thickness;
        this.leadEquivalent = leadEquivalent;
    }

    @Override
    protected RequestBuilder constructRequestBuilder() {
        MaterialDto dto = new MaterialDto(name, density, voltage, thickness, leadEquivalent);
        String encodedUrl;
        if (thickness == 0 && leadEquivalent != 0) {
            encodedUrl = url;
            return new RequestBuilder(encodedUrl,jsonConverter.toJson(dto));
        } else if (thickness != 0 && leadEquivalent == 0) {
            encodedUrl = MAT_LEAD_EQUIVALENT;
            return new RequestBuilder(encodedUrl,jsonConverter.toJson(dto));
        }
         else {
            log.warn("wasn't able to generate request builder because thickness and lead equivalent are both zero");
            return new RequestBuilder("ERROR",jsonConverter.toJson(dto));
        }
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
