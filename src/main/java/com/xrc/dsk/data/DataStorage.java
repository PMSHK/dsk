package com.xrc.dsk.data;

import com.xrc.dsk.dto.WindowDto;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataStorage {
    private static DataStorage instance;
    private final ObjectProperty<WindowDto> windowDto = new SimpleObjectProperty<>();

    public static DataStorage getInstance() {
        if (instance == null) {
            synchronized (DataStorage.class) {
                if (instance == null) {
                    instance = new DataStorage();
                }
            }
        }
        return instance;
    }

    public WindowDto getWindowDto() {
        return windowDto.get();
    }

    public void setWindowDto(WindowDto windowDto) {
        this.windowDto.set(windowDto);
    }

    public ObjectProperty<WindowDto> windowDtoProperty() {
        return windowDto;
    }
}
