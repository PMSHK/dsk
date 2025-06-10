package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.TextFormDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TextFormDataViewModel implements DataViewModel<TextFormDataDto> {
    private StringProperty wallSign = new SimpleStringProperty();
    private StringProperty purposeAdjacentRoom = new SimpleStringProperty();
    private StringProperty personalCategory = new SimpleStringProperty();

    public TextFormDataViewModel(TextFormDataDto dto) {
        wallSign.set(dto.getWallSign());
        purposeAdjacentRoom.set(dto.getPurposeAdjacentRoom());
        personalCategory.set(dto.getPersonalCategory());
    }

    @Override
    public TextFormDataDto toDto() {
        return new TextFormDataDto(wallSign.get(), purposeAdjacentRoom.get(), personalCategory.get());
    }

    @Override
    public void fromDto(AppData dto) {
        TextFormDataDto data = (TextFormDataDto) dto;
        this.wallSign.set(data.getWallSign());
        this.purposeAdjacentRoom.set(data.getPurposeAdjacentRoom());
        this.personalCategory.set(data.getPersonalCategory());
    }
}
