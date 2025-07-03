package com.xrc.dsk.events;

import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdditionalMatEvent {
    private Double demandedEquivalent;
    private Double existedEquivalent;
    private Integer panelId;
    private PanelDataViewModel viewModel;
}
