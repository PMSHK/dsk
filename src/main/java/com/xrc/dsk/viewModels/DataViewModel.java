package com.xrc.dsk.viewModels;

import com.xrc.dsk.data.bin.AppData;

public abstract class DataViewModel<T extends AppData> {
    public DataViewModel() {
        init();
    }

    public DataViewModel(AppData data) {
        this();
        if (data != null) {
            fromDto(data);
        }
    }

    public abstract T toDto();

    public abstract void fromDto(AppData dto);

    public abstract void init();
}
