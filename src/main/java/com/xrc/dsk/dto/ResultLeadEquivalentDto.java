package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultLeadEquivalentDto {
    private Double calculatedLeadEquivalent;
    private Double existedLeadEquivalent;
}
