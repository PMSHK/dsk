package com.xrc.dsk.dto.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.Filled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

import static com.xrc.dsk.settings.AppParameters.MEDICINE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PanelDataDto implements Serializable, Filled, AppData {
    private TextFormDataDto textFormDto;
    private ProtectionDataDto protectionDto;
    private List<MatCharacteristicsDataDto> existedMaterialCharacteristicsDtoList;
    private MatCharacteristicsDataDto recommendedMaterialDto;
    private List<OpeningsDataDto> openingDtoList;
    private SourceDataDto sourceDataDto;
    private String additionalLead;

    @Override
    public boolean filled() {
        return sourceDataDto != null && sourceDataDto.filled();
    }

    @Override
    public String getType() {
        return MEDICINE;
    }
}
