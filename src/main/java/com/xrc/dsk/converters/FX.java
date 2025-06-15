package com.xrc.dsk.converters;

import javafx.beans.property.ListProperty;

import java.util.List;
import java.util.function.Function;

public final class FX {
    public static <T, D> void updateList(ListProperty<D> list, List<T> inputList, Function<T, D> mapper) {
        list.clear();
        if (inputList != null) {
            list.addAll(inputList.stream().map(mapper).toList());
        }
    }
}
