package com.xrc.dsk.dto.medicine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xrc.dsk.data.bin.AppData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.xrc.dsk.settings.AppParameters.MEDICINE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialInfoDataDto implements Serializable, AppData {
    private String name;
    private Float density;
    @JsonIgnore
    private String materialName;

    public MaterialInfoDataDto(String materialName, Float density) {

        this.density = density;
        this.name = materialName;
        this.materialName = materialName + " " + density;
    }

    @Override
    public String getType() {
        return MEDICINE;
    }

    public boolean filled(){
        return density != null && name!=null && !name.isBlank();
    }
}