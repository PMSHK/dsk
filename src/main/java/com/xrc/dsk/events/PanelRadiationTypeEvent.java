package com.xrc.dsk.events;

import com.xrc.dsk.viewModels.medicine.RadTypeDataViewModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PanelRadiationTypeEvent implements RadTypeEvents {
    private final RadTypeDataViewModel viewModel;
}
