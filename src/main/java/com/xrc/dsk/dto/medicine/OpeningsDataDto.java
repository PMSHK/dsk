package com.xrc.dsk.dto.medicine;

import com.xrc.dsk.data.bin.AppData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.xrc.dsk.settings.AppParameters.MEDICINE;

@Data
@NoArgsConstructor
public class OpeningsDataDto implements Serializable, AppData {
    private String name;
    private String thickness;

    public OpeningsDataDto(String name, String thickness) {
        this.name = name;
        this.thickness = thickness;
    }

    public OpeningsDataDto(String name) {
        this.name = name;
        this.thickness = "0.0";
    }

    @Override
    public String getType() {
        return MEDICINE;
    }
}
