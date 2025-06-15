package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.FX;
import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import com.xrc.dsk.dto.medicine.SourceDataDto;
import com.xrc.dsk.dto.medicine.TextFormDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public class PanelDataViewModel implements DataViewModel<PanelDataDto> {

    private ObjectProperty<TextFormDataViewModel> textFormViewModel = new SimpleObjectProperty<>();
    private ObjectProperty<ProtectionDataViewModel> protectionViewModel = new SimpleObjectProperty<>();
    private ListProperty<MatCharacteristicsDataViewModel> existedMatCharacteristicsViewModelList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<MatCharacteristicsDataViewModel> recommendedMatViewModel = new SimpleObjectProperty<>();
    private ListProperty<OpeningsViewModel> openingViewModelList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<SourceDataViewModel> sourceDataViewModel = new SimpleObjectProperty<>();
    private StringProperty additionalLeadViewModel = new SimpleStringProperty();

    public PanelDataViewModel(PanelDataDto dto) {
        if (dto != null) {
            this.fromDto(dto);
        }
    }

    @Override
    public PanelDataDto toDto() {
        TextFormDataDto textFormDataDto = NullChecker.getDtoOrDefault(textFormViewModel,TextFormDataViewModel::toDto, new TextFormDataDto());
        ProtectionDataDto protectionDataDto = protectionViewModel.get() != null ? protectionViewModel.get().toDto() : null;
        List<MatCharacteristicsDataDto> existedMatCharacteristicsDtolList = existedMatCharacteristicsViewModelList.stream().
                filter(Objects::nonNull).
                map(MatCharacteristicsDataViewModel::toDto).toList();
        MatCharacteristicsDataDto recommendedMatDto = recommendedMatViewModel.get() != null ? recommendedMatViewModel.get().toDto() : null;
        List<OpeningsDataDto> openingsDtolList = openingViewModelList.stream().
                filter(Objects::nonNull).
                map(OpeningsViewModel::toDto).toList();
        SourceDataDto sourceDataDto = sourceDataViewModel.get() != null ? sourceDataViewModel.get().toDto() : null;
        return new PanelDataDto(textFormDataDto, protectionDataDto, existedMatCharacteristicsDtolList,
                recommendedMatDto, openingsDtolList, sourceDataDto, additionalLeadViewModel.get());
    }

    @Override
    public void fromDto(AppData dto) {
        PanelDataDto data = (PanelDataDto) dto;
        this.textFormViewModel.set(
                NullChecker.getValueOrDefault(data.getTextFormDto(), TextFormDataViewModel::new, new TextFormDataViewModel(new TextFormDataDto()))
        );
        this.protectionViewModel.set(
                NullChecker.getValueOrDefault(data.getProtectionDto(), ProtectionDataViewModel::new, new ProtectionDataViewModel(new ProtectionDataDto()))
        );
        FX.updateList(this.existedMatCharacteristicsViewModelList, data.getExistedMaterialCharacteristicsDtoList(),MatCharacteristicsDataViewModel::new);
        this.recommendedMatViewModel.set(
                NullChecker.getValueOrDefault(data.getRecommendedMaterialDto(), MatCharacteristicsDataViewModel::new, new MatCharacteristicsDataViewModel(new MatCharacteristicsDataDto()))
        );
        FX.updateList(this.openingViewModelList, data.getOpeningDtoList(),OpeningsViewModel::new);
        this.sourceDataViewModel.set(
                NullChecker.getValueOrDefault(data.getSourceDataDto(), SourceDataViewModel::new, new SourceDataViewModel(new SourceDataDto()))
        );
        this.additionalLeadViewModel.set(NullChecker.getString(data.getAdditionalLead(),"Не требуется"));
    }
}
