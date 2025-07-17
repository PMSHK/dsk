package com.xrc.dsk.converters;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class NullChecker {
    public static <T, D> List<D> getList(List<T> inputList, Function<T, D> mapper) {
        return inputList == null ? List.of() : inputList.stream().map(mapper).toList();
    }

    public static <T, D> List<D> getList(ListProperty<T> inputList, Function<T, D> mapper) {
        return inputList == null ? List.of() : inputList.stream().filter(Objects::nonNull).map(mapper).toList();
    }

    public static <T> ObservableList<T> getObservableList(ListProperty<T> inputList) {
        if (inputList == null) {
            inputList = new SimpleListProperty<>(FXCollections.observableArrayList());
        }
        if (inputList.get() == null) {
            inputList.set(FXCollections.observableArrayList());
        }
        return inputList.get();
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

    public static <T extends ObjectProperty, R> R getValueOrDefault(T input, R defaultValue) {
        if (input.get() == null) {
            input.set(defaultValue);
        }
        return (R) input.get();
    }

    public static <T> T getValueOrSetDefault(Property<T> property, T defaultValue) {
        if (property == null) {
            return defaultValue;
        }
        T value = property.getValue();
        if (value == null) {
            property.setValue(defaultValue);
        }
        return property.getValue();
    }

    public static String getString(String input, String defaultString) {
        return input == null ? defaultString : input;
    }

}
