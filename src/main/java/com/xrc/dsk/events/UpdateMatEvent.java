package com.xrc.dsk.events;

import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateMatEvent {
    private final MatCharacteristicsDataViewModel viewModel;
//    private final MatCharacteristicsDataDto materialCharacteristicsDto;
//    private final Long voltage;
//    private final Boolean basedOnThickness;
}
