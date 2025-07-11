package com.xrc.dsk.events;

import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.viewModels.medicine.RadTypeDataViewModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RadiationTypeEvent implements RadTypeEvents {
    private final RadTypeDataViewModel viewModel;
}
