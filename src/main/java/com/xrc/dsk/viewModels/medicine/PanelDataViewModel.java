package com.xrc.dsk.viewModels.medicine;

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

import java.util.List;
import java.util.Optional;

public class PanelDataViewModel implements DataViewModel<PanelDataDto> {

    private ObjectProperty<TextFormDataViewModel> textFormViewModel = new SimpleObjectProperty<>();
    private ObjectProperty<ProtectionDataViewModel> protectionViewModel = new SimpleObjectProperty<>();
    private ListProperty<MatCharacteristicsDataViewModel> existedMatCharacteristicsViewModelList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<MatCharacteristicsDataViewModel> recommendedMatViewModel = new SimpleObjectProperty<>();
    private ListProperty<OpeningsViewModel> openingViewModelList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<SourceDataViewModel> sourceDataViewModel = new SimpleObjectProperty<>();
    private StringProperty additionalLeadViewModel = new SimpleStringProperty();

    public PanelDataViewModel(PanelDataDto dto) {

    }

    @Override
    public PanelDataDto toDto() {
        TextFormDataDto textFormDataDto = textFormViewModel.get() != null ? textFormViewModel.get().toDto() : new TextFormDataDto();
        ProtectionDataDto protectionDataDto = protectionViewModel.get() != null ? protectionViewModel.get().toDto() : new ProtectionDataDto();
        List<MatCharacteristicsDataDto> existedMatCharacteristicsDtolList = existedMatCharacteristicsViewModelList.stream().
                map(MatCharacteristicsDataViewModel::toDto).toList();
        MatCharacteristicsDataDto recommendedMatDto = recommendedMatViewModel.get() != null ? recommendedMatViewModel.get().toDto() : new MatCharacteristicsDataDto();
        List<OpeningsDataDto> openingsDtolList = openingViewModelList.stream().
                map(OpeningsViewModel::toDto).toList();
        SourceDataDto sourceDataDto = sourceDataViewModel.get() != null ? sourceDataViewModel.get().toDto() : new SourceDataDto();
        return new PanelDataDto(textFormDataDto, protectionDataDto, existedMatCharacteristicsDtolList,
                recommendedMatDto, openingsDtolList, sourceDataDto, additionalLeadViewModel.get());
    }

    @Override
    public void fromDto(AppData dto) {
        PanelDataDto data = (PanelDataDto) dto;
        this.textFormViewModel.set(Optional.ofNullable(data.getTextFormDto()).
                map(TextFormDataViewModel::new).orElse(new TextFormDataViewModel()));
        this.protectionViewModel.set(Optional.ofNullable(data.getProtectionDto()).
                map(ProtectionDataViewModel::new).orElse(new ProtectionDataViewModel())
        );
        this.existedMatCharacteristicsViewModelList.set(FXCollections.observableArrayList(
                Optional.ofNullable(data.getExistedMaterialCharacteristicsDtoList()).
                        stream().flatMap(List::stream).
                        map(MatCharacteristicsDataViewModel::new).toList()
        ));
        this.recommendedMatViewModel.set(Optional.ofNullable(data.getRecommendedMaterialDto()).
                map(MatCharacteristicsDataViewModel::new).orElse(new MatCharacteristicsDataViewModel()));
        this.openingViewModelList.set(FXCollections.observableArrayList(
                Optional.ofNullable(data.getOpeningDtoList()).stream()
                        .flatMap(List::stream)
                        .map(OpeningsViewModel::new).toList()
        ));
        this.sourceDataViewModel.set(Optional.ofNullable(data.getSourceDataDto()).
                map(SourceDataViewModel::new).orElse(new SourceDataViewModel()));
        this.additionalLeadViewModel.set(Optional.ofNullable(data.getAdditionalLead()).orElse("Не требуется"));
    }
}
