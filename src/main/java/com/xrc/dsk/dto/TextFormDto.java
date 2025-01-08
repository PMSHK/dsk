package com.xrc.dsk.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class TextFormDto implements Serializable {
    private StringProperty wallSign = new SimpleStringProperty();
    private StringProperty purposeAdjacentRoom = new SimpleStringProperty();
    private StringProperty personalCategory = new SimpleStringProperty();

    public StringProperty wallSignProperty() {
        return wallSign;
    }

    public StringProperty purposeAdjacentRoomProperty() {
        return purposeAdjacentRoom;
    }

    public StringProperty personalCategoryProperty() {
        return personalCategory;
    }

    public void setWallSign(String wallSign) {
        this.wallSign.set(wallSign);
    }

    public void setPurposeAdjacentRoom(String purposeAdjacentRoom) {
        this.purposeAdjacentRoom.set(purposeAdjacentRoom);
    }

    public void setPersonalCategory(String personalCategory) {
        this.personalCategory.set(personalCategory);
    }

    public String getWallSign() {
        return wallSign.get();
    }

    public String getPurposeAdjacentRoom() {
        return purposeAdjacentRoom.get();
    }

    public String getPersonalCategory() {
        return personalCategory.get();
    }

}
