package com.xrc.dsk.connection;


import com.xrc.dsk.converters.JsonConverter;
import com.xrc.dsk.converters.NumbersFormatter;
import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.dto.ResultLeadEquivalentDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

import static com.xrc.dsk.settings.ApiEndpoints.ADDITIONAL_PROTECTION;

@Slf4j
public class ConnectionService {
    private final HttpClient httpClient;
    private RequestBuilder requestBuilder;
    private JsonConverter jsonConverter;

    public ConnectionService() {
        httpClient = HttpClient.newHttpClient();
    }

    public List<String> getAllMaterials() {
        AllMaterialsRequestBuilder allMatBuilder = new AllMaterialsRequestBuilder("GET");
        return allMatBuilder.constructRequest(MaterialInfoDataDto.class).join();
    }

    public RadTypeDataDto getEquipTypeParameters(String typeName, String type) {
        EquipTypeRequestBuilder equipTypeBuilder = new EquipTypeRequestBuilder("GET", typeName, type);
        return equipTypeBuilder.constructRequest(RadTypeDataDto.class).join();
    }

    public Double getRadExit(double voltage) {
        RadExitRequestBuilder radExitBuilder = new RadExitRequestBuilder("POST", voltage);
        return radExitBuilder.constructRequest(Double.class).join();
    }

    public List<String> getEquipmentType() {
        RadTypeRequestBuilder radTypeBuilder = new RadTypeRequestBuilder("POST");
        return radTypeBuilder.constructRequest(String.class).join();
    }

    public List<String> getPersonalCategories() {
        PersonalCategoriesRequestBuilder personalCategoriesBuilder = new PersonalCategoriesRequestBuilder("GET");
        return personalCategoriesBuilder.constructRequest(String.class).join();
    }

    public List<Double> getDirectionCoefficients() {
        DirectionCoefficientsRequestBuilder directionCoefficientsBuilder = new DirectionCoefficientsRequestBuilder("GET");
        return directionCoefficientsBuilder.constructRequest(Double.class).join();
    }

    public String getDmdByCategory(String category) {
        DMDRequestBuilder dmdBuilder = new DMDRequestBuilder("GET", category);
        return dmdBuilder.constructRequest(String.class).join();
    }

    public ProtectionDataDto getDemandedLeadEquivalent(KParamDto dto) {
        DemandedLeadRequestBuilder demandedLeadRequestBuilder = new DemandedLeadRequestBuilder("POST", dto);
        return demandedLeadRequestBuilder.constructRequest(ProtectionDataDto.class).join();
    }

    public double getMaterialCharacteristics(
            String name,
            double density,
            double voltage,
            double thickness,
            double leadEquivalent) {
        density = NumbersFormatter.formatWithPrecision(density, 2).doubleValue();
        MaterialCharacteristicsRequestBuilder builder = new MaterialCharacteristicsRequestBuilder("POST",
                name,
                density,
                voltage,
                thickness,
                leadEquivalent
        );
        return builder.constructRequest(Double.class).join();
    }

    public String getAdditionalProtection(Double demandedLeadEquivalent, Double existedLeadEquivalent) {
        jsonConverter = new JsonConverter();
        ResultLeadEquivalentDto dto = new ResultLeadEquivalentDto(demandedLeadEquivalent, existedLeadEquivalent);
        requestBuilder = new RequestBuilder(ADDITIONAL_PROTECTION, jsonConverter.toJson(dto));

        return httpClient.sendAsync(requestBuilder.createRequest("POST"), HttpResponse.BodyHandlers.ofString())
                .thenApply((http) -> {
                    if (http.statusCode() / 100 == 2) {
                        return http.body();
                    } else {
                        return dto.getCalculatedLeadEquivalent() + " мм";
                    }
                }).
                exceptionally(e -> {
                    log.info("Exception occurred withing getting additional lead equivalent ", e);
                    return dto.getCalculatedLeadEquivalent() + " мм";
                })
                .join();
    }

    public String getOpeningsProtection(String name, Double demandedLeadEquivalent, Double precision) {
        OpeningsRequestBuilder openingsBuilder = new OpeningsRequestBuilder("GET", name, demandedLeadEquivalent, precision);
        return openingsBuilder.constructRequest(String.class).join();
    }

    public List<String> getAllOpenings() {
        AllOpeningsRequestBuilder allOpeningsBuilder = new AllOpeningsRequestBuilder("GET");
        return allOpeningsBuilder.constructRequest(OpeningsDataDto.class).join();
    }
}
