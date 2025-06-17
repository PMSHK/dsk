package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.TextFormDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextFormDataViewModel extends DataViewModel<TextFormDataDto> {
    private StringProperty wallSignProperty;
    private StringProperty purposeAdjacentRoomProperty;
    private StringProperty personalCategoryProperty;

    public TextFormDataViewModel() {
        super();
    }

    public TextFormDataViewModel(TextFormDataDto dto) {
        super(dto);
    }

    @Override
    public TextFormDataDto toDto() {
        return new TextFormDataDto(
                getWallSign(),
                getPurposeAdjacentRoom(),
                getPersonalCategory());
    }

    @Override
    public void fromDto(AppData dto) {
        TextFormDataDto data = (TextFormDataDto) dto;
        this.wallSignProperty.set(NullChecker.getValueOrDefault(data.getWallSign(), ""));
        this.purposeAdjacentRoomProperty.set(NullChecker.getValueOrDefault(data.getPurposeAdjacentRoom(), ""));
        this.personalCategoryProperty.set(NullChecker.getValueOrDefault(data.getPersonalCategory(), ""));
    }

    @Override
    public void init() {
        this.wallSignProperty = new SimpleStringProperty();
        this.purposeAdjacentRoomProperty = new SimpleStringProperty();
        this.personalCategoryProperty = new SimpleStringProperty();
    }

    public String getWallSign() {
        return NullChecker.getString(wallSignProperty.get(), "");
    }

    public String getPurposeAdjacentRoom() {
        return NullChecker.getString(purposeAdjacentRoomProperty.get(), "");
    }

    public String getPersonalCategory() {
        return NullChecker.getString(personalCategoryProperty.get(), "");
    }
}
