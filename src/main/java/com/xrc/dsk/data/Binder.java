package com.xrc.dsk.data;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@NoArgsConstructor
public class Binder {
    public void bindLongPropertyToString(
            Property<String> firstProperty
            , LongProperty secondProperty
            , Long value
            , Consumer<Long> consumer) {
        secondProperty.setValue(value);
        Bindings.bindBidirectional(firstProperty, secondProperty, new NumberStringConverter());
        secondProperty.addListener((observable, oldValue, newValue) -> {
            consumer.accept(newValue.longValue());
        });
    }

    public void bindDoublePropertyToDouble(
            ObjectProperty<Double> firstProperty
            , DoubleProperty secondProperty
            , Double value
            , Consumer<Double> consumer) {
        secondProperty.setValue(value);
        Bindings.bindBidirectional(firstProperty, secondProperty.asObject());
        secondProperty.addListener((observable, oldValue, newValue) -> {
            consumer.accept(newValue.doubleValue());
        });
    }

    public void bindTextPropertyToString(
            Property<String> firstProperty,
            Property<String> secondProperty){
        Bindings.bindBidirectional(firstProperty,secondProperty);
    }

    public void bindDoublePropertyToString(
            Property<String> firstProperty
            , DoubleProperty secondProperty
            , Double value
            , Consumer<Double> consumer) {
        secondProperty.setValue(value);
        Bindings.bindBidirectional(firstProperty, secondProperty, new NumberStringConverter());
        secondProperty.addListener((observable, oldValue, newValue) -> {
            consumer.accept(newValue.doubleValue());
        });
    }
}
