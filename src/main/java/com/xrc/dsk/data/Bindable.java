package com.xrc.dsk.data;

import com.xrc.dsk.dto.WindowDto;

import java.util.Map;

public interface Bindable {
    void bind(WindowDto dto, Map<String, Object[]> components);
}
