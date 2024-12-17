package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RadiationTypeDto implements Serializable {
    private String name;
    private long voltage;
    private long radiationExit;
}
