package com.xrc.dsk.data.bin;

import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AppSession {
    private final ObjectProperty<DataViewModel<? extends AppData>> currentData = new SimpleObjectProperty<>();

    public ObjectProperty<DataViewModel<? extends AppData>> currentDataProperty() {
        return currentData;
    }

    public void setCurrentData(DataViewModel<? extends AppData> data) {
        currentData.set(data);
    }

    public DataViewModel<? extends AppData> getCurrentData() {
        return currentData.get();
    }
}
