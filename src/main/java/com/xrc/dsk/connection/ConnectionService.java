package com.xrc.dsk.connection;


import com.xrc.dsk.converters.JsonConverter;
import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.dto.KermaParamDto;
import com.xrc.dsk.dto.MaterialInfoDto;
import com.xrc.dsk.dto.ProtectionDto;
import com.xrc.dsk.dto.RadiationTypeDto;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.xrc.dsk.data.AppParameters.MCS_HOST;
import static com.xrc.dsk.data.AppParameters.MCS_PORT;

public class ConnectionService {
    private final HttpClient httpClient;
    private HttpRequest request;
    private RequestBuilder requestBuilder;
    private JsonConverter jsonConverter;

    public ConnectionService() {
        httpClient = HttpClient.newHttpClient();
    }

    public List<String> getAllMaterials() {
        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/protection/all_materials");
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("GET"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    return jsonConverter.listFromJson(json, MaterialInfoDto.class)
                            .stream()
                            .map(dto -> dto.getName() + " " + dto.getDensity())
                            .toList();
                }).join();
    }

    public RadiationTypeDto getEquipTypeParameters(String typeName, String type) {
        String encodedTypeName = URLEncoder.encode(typeName, StandardCharsets.UTF_8);
        encodedTypeName = encodedTypeName.replace("+", "%20");
        String encodedType = URLEncoder.encode(type, StandardCharsets.UTF_8);
        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/radiation_type/" + encodedTypeName + "?type=" + encodedType);
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("GET"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    return jsonConverter.fromJson(json, RadiationTypeDto.class);
                }).join();
    }

    public Double getRadExit(double voltage) {
        KermaParamDto kParamDto = new KermaParamDto(voltage);
        String body = jsonConverter.toJson(kParamDto);

        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/calculation/kerma", body);
        return httpClient.sendAsync(requestBuilder.createRequest("POST"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    return jsonConverter.fromJson(json, Double.class);
                }).join();
    }

    public List<String> getEquipmentType() {
        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/radiation_type/types", "{}");
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("POST"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    return jsonConverter.listFromJson(json, String.class);
                }).join();
    }

    public List<String> getPersonalCategories() {
        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/calculation_info/room_categories");
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("GET"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    return jsonConverter.listFromJson(json, String.class);
                }).join();
    }

    public List<Double> getDirectionCoefficients() {
        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/calculation_info/direction_coefficient");
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("GET"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    return jsonConverter.listFromJson(json, Double.class);
                }).join();
    }

    public String getDmdByCategory(String category) {
        String categoryEncoded = URLEncoder.encode(category, StandardCharsets.UTF_8);
        categoryEncoded = categoryEncoded.replace("+", "%20");
        requestBuilder = new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/calculation_info/dmd?room_category=" + categoryEncoded);
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("GET"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> jsonConverter.fromJson(json, Double.class))
                .thenApply(String::valueOf).join();
    }

    public ProtectionDto getDemandedLeadEquivalent(KParamDto dto){

        requestBuilder =  new RequestBuilder(MCS_HOST + ":" + MCS_PORT + "/calculation/protection",jsonConverter.toJson(dto));
        jsonConverter = new JsonConverter();
        return httpClient.sendAsync(requestBuilder.createRequest("POST"), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json->jsonConverter.fromJson(json, ProtectionDto.class))
                .join();
    }

}
