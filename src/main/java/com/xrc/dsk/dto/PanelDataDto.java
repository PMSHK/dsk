package com.xrc.dsk.dto;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class PanelDataDto implements Serializable, Filled {
    private ObjectProperty<TextFormDto> textFormDto = new SimpleObjectProperty<>();
    private ObjectProperty<ProtectionDto> protectionDto = new SimpleObjectProperty<>();
    private ListProperty<MaterialDto> existedMaterialDtoList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<MaterialDto> recommendedMaterialDto = new SimpleObjectProperty<>();
    private ListProperty<OpeningDto> openingDtoList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<SourceDataDto> sourceDataDto = new SimpleObjectProperty<>();

    public ObjectProperty<TextFormDto> textFormDtoProperty() {
        return textFormDto;
    }

    public ObjectProperty<ProtectionDto> protectionDtoProperty() {
        return protectionDto;
    }

    public ListProperty<MaterialDto> existedMaterialDtoListProperty() {
        return existedMaterialDtoList;
    }

    public ObjectProperty<MaterialDto> recommendedMaterialDtoProperty() {
        return recommendedMaterialDto;
    }

    public ListProperty<OpeningDto> openingDtoListProperty() {
        return openingDtoList;
    }

    public ObjectProperty<SourceDataDto> sourceDataDtoProperty() {
        return sourceDataDto;
    }

    public void setTextFormDto(TextFormDto textFormDto) {
        this.textFormDtoProperty().set(textFormDto);
    }

    public void setProtectionDto(ProtectionDto protectionDto) {
        this.protectionDtoProperty().set(protectionDto);
    }

    public void setExistedMaterialDtoList(ListProperty<MaterialDto> existedMaterialDtoList) {
        this.existedMaterialDtoListProperty().set(existedMaterialDtoList);
    }

    public void setRecommendedMaterialDto(MaterialDto recommendedMaterialDto) {
        this.recommendedMaterialDtoProperty().set(recommendedMaterialDto);
    }

    public void setOpeningDtoList(ListProperty<OpeningDto> openingDtoList) {
        this.openingDtoListProperty().set(openingDtoList);
    }

    public void setSourceDataDto(SourceDataDto sourceDataDto) {
        this.sourceDataDtoProperty().set(sourceDataDto);
    }

    public TextFormDto getTextFormDto() {
        if (textFormDto.get() == null) {
            textFormDto.set(new TextFormDto());
        }
        return textFormDtoProperty().get();
    }

    public ProtectionDto getProtectionDto() {
        if (protectionDto.get() == null) {
            protectionDto.set(new ProtectionDto());
        }
        return protectionDtoProperty().get();
    }

    public ObservableList<MaterialDto> getExistedMaterialDtoList() {

        return existedMaterialDtoListProperty().get();
    }

    public MaterialDto getRecommendedMaterialDto() {
        if (recommendedMaterialDto.get() == null) {
            recommendedMaterialDto.set(new MaterialDto());
        }
        return recommendedMaterialDtoProperty().get();
    }

    public ObservableList<OpeningDto> getOpeningDtoList() {

        return openingDtoListProperty().get();
    }

    public SourceDataDto getSourceDataDto() {
        if (sourceDataDto.get() == null) {
            sourceDataDto.set(new SourceDataDto());
        }
        return sourceDataDtoProperty().get();
    }

    @Override
    public boolean filled() {
        return sourceDataDto.get()!=null && sourceDataDto.get().filled();
    }
}
