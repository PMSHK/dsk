package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialInfoDto implements Serializable {
    private String name;
    private Float density;
}