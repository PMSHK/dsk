package com.xrc.dsk.events;

import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MaterialEvent {
    private final MatCharacteristicsDataViewModel viewModel;
}
