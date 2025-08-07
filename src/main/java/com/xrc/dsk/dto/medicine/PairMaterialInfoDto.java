package com.xrc.dsk.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PairMaterialInfoDto {
    private final MaterialInfoDataDto targetMaterial;
    private final MaterialInfoDataDto sourceMaterial;
}
