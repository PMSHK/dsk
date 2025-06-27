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
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.xrc.dsk.settings.AppParameters.NOT_DEMAND;

@Getter
@Setter
public class PanelDataViewModel extends DataViewModel<PanelDataDto> {

    private ObjectProperty<TextFormDataViewModel> textFormViewModelProperty;
    private ObjectProperty<ProtectionDataViewModel> protectionViewModelProperty;
    private ListProperty<MatCharacteristicsDataViewModel> existedMatCharacteristicsViewModelListProperty;
    private ObjectProperty<MatCharacteristicsDataViewModel> recommendedMatViewModelProperty;
    private ListProperty<OpeningsViewModel> openingViewModelListProperty;
    private ObjectProperty<SourceDataViewModel> sourceDataViewModelProperty;
    private StringProperty additionalLeadViewModelProperty;

    public PanelDataViewModel() {
        super();
    }

    public PanelDataViewModel(PanelDataDto dto) {
        super(dto);
    }

    @Override
    public PanelDataDto toDto() {
        TextFormDataDto textFormDataDto = NullChecker.getDtoOrDefault(textFormViewModelProperty, TextFormDataViewModel::toDto, new TextFormDataDto());
        ProtectionDataDto protectionDataDto = NullChecker.getDtoOrDefault(protectionViewModelProperty, ProtectionDataViewModel::toDto, new ProtectionDataDto());
        List<MatCharacteristicsDataDto> existedMatCharacteristicsDtolList = NullChecker.getList(existedMatCharacteristicsViewModelListProperty, MatCharacteristicsDataViewModel::toDto);
        MatCharacteristicsDataDto recommendedMatDto = NullChecker.getDtoOrDefault(recommendedMatViewModelProperty, MatCharacteristicsDataViewModel::toDto, new MatCharacteristicsDataDto());
        List<OpeningsDataDto> openingsDtolList = NullChecker.getList(openingViewModelListProperty, OpeningsViewModel::toDto);
        SourceDataDto sourceDataDto = NullChecker.getDtoOrDefault(sourceDataViewModelProperty, SourceDataViewModel::toDto, new SourceDataDto());
        String additionalLayer = NullChecker.getString(additionalLeadViewModelProperty.get(), NOT_DEMAND);
        return new PanelDataDto(textFormDataDto, protectionDataDto, existedMatCharacteristicsDtolList,
                recommendedMatDto, openingsDtolList, sourceDataDto, additionalLayer);
    }

    @Override
    public void fromDto(AppData dto) {
        PanelDataDto data = (PanelDataDto) dto;
        this.textFormViewModelProperty.set(
                NullChecker.getValueOrDefault(data.getTextFormDto(), TextFormDataViewModel::new, new TextFormDataViewModel())
        );
        this.protectionViewModelProperty.set(
                NullChecker.getValueOrDefault(data.getProtectionDto(), ProtectionDataViewModel::new, new ProtectionDataViewModel())
        );
        FX.updateList(this.existedMatCharacteristicsViewModelListProperty, data.getExistedMaterialCharacteristicsDtoList(), MatCharacteristicsDataViewModel::new);
        this.recommendedMatViewModelProperty.set(
                NullChecker.getValueOrDefault(data.getRecommendedMaterialDto(), MatCharacteristicsDataViewModel::new, new MatCharacteristicsDataViewModel())
        );
        FX.updateList(this.openingViewModelListProperty, data.getOpeningDtoList(), OpeningsViewModel::new);
        this.sourceDataViewModelProperty.set(
                NullChecker.getValueOrDefault(data.getSourceDataDto(), SourceDataViewModel::new, new SourceDataViewModel())
        );
        this.additionalLeadViewModelProperty.set(NullChecker.getString(data.getAdditionalLead(), NOT_DEMAND));
    }

    @Override
    public void init() {
        this.textFormViewModelProperty = new SimpleObjectProperty<>();
        this.protectionViewModelProperty = new SimpleObjectProperty<>();
        this.existedMatCharacteristicsViewModelListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.recommendedMatViewModelProperty = new SimpleObjectProperty<>();
        this.openingViewModelListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.sourceDataViewModelProperty = new SimpleObjectProperty<>();
        this.additionalLeadViewModelProperty = new SimpleStringProperty();
    }

    public TextFormDataViewModel getTextFormViewModel() {
        return NullChecker.getValueOrDefault(textFormViewModelProperty, new TextFormDataViewModel());
    }

    public ProtectionDataViewModel getProtectionViewModel() {
        return NullChecker.getValueOrDefault(protectionViewModelProperty, new ProtectionDataViewModel());
    }

    public ObservableList<MatCharacteristicsDataViewModel> getExistedMatCharacteristicsViewModelList() {
        return NullChecker.getObservableList(existedMatCharacteristicsViewModelListProperty);
    }

    public MatCharacteristicsDataViewModel getRecommendedMatViewModel() {
        return NullChecker.getValueOrDefault(recommendedMatViewModelProperty, new MatCharacteristicsDataViewModel());
    }

    public ObservableList<OpeningsViewModel> getOpeningDtoList() {
        return NullChecker.getObservableList(openingViewModelListProperty);
    }

    public SourceDataViewModel getSourceDataViewModel() {
        return NullChecker.getValueOrDefault(sourceDataViewModelProperty, new SourceDataViewModel());
    }

    public String getAdditionalLead() {
        return NullChecker.getString(additionalLeadViewModelProperty.get(), NOT_DEMAND);
    }

}
