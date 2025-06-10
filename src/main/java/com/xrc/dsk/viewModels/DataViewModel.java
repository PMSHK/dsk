package com.xrc.dsk.viewModels;

import com.xrc.dsk.data.bin.AppData;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public interface DataViewModel<T extends AppData> {
//    private final ObjectProperty<T> data;

//    public DataViewModel(T data) {
//        this.data = new SimpleObjectProperty<T>(data);
//    }
//
//    public ObjectProperty<T> dataProperty() {
//        return data;
//    }
//
//    public T getData() {
//        return data.get();
//    }
//
//    public void setData(T data) {
//        this.data.set(data);
//    }

    public T toDto();
    public void fromDto(AppData dto);
}
