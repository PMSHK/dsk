package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.TextFormDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TextFormDataViewModel implements DataViewModel<TextFormDataDto> {
    private StringProperty wallSign = new SimpleStringProperty();
    private StringProperty purposeAdjacentRoom = new SimpleStringProperty();
    private StringProperty personalCategory = new SimpleStringProperty();

    public TextFormDataViewModel(TextFormDataDto dto) {
        wallSign.set(NullChecker.getString(dto.getWallSign(),""));
        purposeAdjacentRoom.set(NullChecker.getString(dto.getPurposeAdjacentRoom(),""));
        personalCategory.set(NullChecker.getString(dto.getPersonalCategory(),""));
    }

    @Override
    public TextFormDataDto toDto() {
        return new TextFormDataDto(wallSign.get(), purposeAdjacentRoom.get(), personalCategory.get());
    }

    @Override
    public void fromDto(AppData dto) {
        TextFormDataDto data = (TextFormDataDto) dto;
        this.wallSign.set(NullChecker.getValueOrDefault(data.getWallSign(),""));
        this.purposeAdjacentRoom.set(NullChecker.getValueOrDefault(data.getPurposeAdjacentRoom(),""));
        this.personalCategory.set(NullChecker.getValueOrDefault(data.getPersonalCategory(),""));
    }
}
