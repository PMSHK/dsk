package com.xrc.dsk.dto.medicine;

import com.xrc.dsk.data.bin.AppData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDataDto implements AppData, Serializable {
    private RadTypeDataDto radiationTypeDto;
    private List<PanelDataDto> panelDataDtoList;

    @Override
    public String getType() {
        return "MEDICINE";
    }
}
