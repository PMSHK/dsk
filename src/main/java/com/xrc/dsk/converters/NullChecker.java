package com.xrc.dsk.converters;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.ObjectProperty;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class NullChecker {
    public static <T, D> List<D> getList(List<T> inputList, Function<T, D> mapper) {
        return inputList == null ? List.of() : inputList.stream().map(mapper).toList();
    }

    public static <T, D> D getValueOrNull(T input, Function<T, D> mapper) {
        return input == null ? null : mapper.apply(input);
    }

    public static <T, D> D getValueOrDefault(T input, Function<T, D> mapper, D defaultValue) {
        return input == null ? defaultValue : mapper.apply(input);
    }

    public static <T extends DataViewModel<D>, D extends AppData> D getDtoOrDefault(ObjectProperty<T> inputProperty,
                                                                                    Function<T, D> toDtoMapper,
                                                                                    D defaultValue
    ) {
        return Optional.ofNullable(inputProperty.get())
                .map(toDtoMapper)
                .orElse(defaultValue);

    }

    public static <T> T getValueOrDefault(T input, T defaultValue) {
        return input == null ? defaultValue : input;
    }

    public static String getString(String input, String defaultString) {
        return input == null ? defaultString : input;
    }
}
