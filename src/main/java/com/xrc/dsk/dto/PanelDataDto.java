package com.xrc.dsk.dto;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PanelDataDto implements Serializable, Filled {
    private static final Logger log = LoggerFactory.getLogger(PanelDataDto.class);
    private ObjectProperty<TextFormDto> textFormDto = new SimpleObjectProperty<>();
    private ObjectProperty<ProtectionDto> protectionDto = new SimpleObjectProperty<>();
    private ListProperty<MaterialCharacteristicsDto> existedMaterialCharacteristicsDtoList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<MaterialCharacteristicsDto> recommendedMaterialDto = new SimpleObjectProperty<>();
    private ListProperty<OpeningDto> openingDtoList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<SourceDataDto> sourceDataDto = new SimpleObjectProperty<>();
    private StringProperty additionalLead = new SimpleStringProperty();

    public ObjectProperty<TextFormDto> textFormDtoProperty() {
        return textFormDto;
    }

    public ObjectProperty<ProtectionDto> protectionDtoProperty() {
        return protectionDto;
    }

    public ListProperty<MaterialCharacteristicsDto> existedMaterialDtoListProperty() {
        return existedMaterialCharacteristicsDtoList;
    }

    public ObjectProperty<MaterialCharacteristicsDto> recommendedMaterialDtoProperty() {
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

    public void setExistedMaterialCharacteristicsDtoList(ListProperty<MaterialCharacteristicsDto> existedMaterialCharacteristicsDtoList) {
        this.existedMaterialDtoListProperty().set(existedMaterialCharacteristicsDtoList);
    }

    public void setRecommendedMaterialDto(MaterialCharacteristicsDto recommendedMaterialCharacteristicsDto) {
        this.recommendedMaterialDtoProperty().set(recommendedMaterialCharacteristicsDto);
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

    public ObservableList<MaterialCharacteristicsDto> getExistedMaterialCharacteristicsDtoList() {

        return existedMaterialDtoListProperty().get();
    }

    public MaterialCharacteristicsDto getRecommendedMaterialDto() {
        if (recommendedMaterialDto.get() == null) {
            recommendedMaterialDto.set(new MaterialCharacteristicsDto());
        }
        return recommendedMaterialDtoProperty().get();
    }

    public ObservableList<OpeningDto> getOpeningDtoList() {

        return openingDtoListProperty().get();
    }

    public StringProperty getAdditionalLead() {
        log.info("getAdditionalLead: {}", additionalLead.get());
        return additionalLead;
    }

    public double getDoubleValueAdditionalLead() {
        log.info("getDoubleValueAdditionalLead: {}", additionalLead.get());
        try {
            String value = additionalLead.get().split(" мм")[0];
            return Double.parseDouble(value);
        } catch (Exception e) {
            log.info("Additional lead value is String: {}", additionalLead.get());
            return -1;
        }
    }

    public void setAdditionalLead(String additionalLead) {
        log.info("Setting additionalLead in panel {} (hashCode={}): value={}, additionalLeadHash={}",
                this.hashCode(), System.identityHashCode(this),
                additionalLead, System.identityHashCode(additionalLead));
        this.additionalLead.set(additionalLead);
    }

    public SourceDataDto getSourceDataDto() {
        if (sourceDataDto.get() == null) {
            sourceDataDto.set(new SourceDataDto());
        }
        return sourceDataDtoProperty().get();
    }

    @Override
    public boolean filled() {
        return sourceDataDto.get() != null && sourceDataDto.get().filled();
    }
}
