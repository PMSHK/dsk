package com.xrc.dsk.handlers;

import javafx.scene.control.ComboBox;

import java.util.NoSuchElementException;

public class ComboBoxHandler<T> extends ElementHandler {
    private ComboBox<?> comboBox;

    public ComboBoxHandler(ComboBox<T> comboBox) {
        super(comboBox);
        this.comboBox = comboBox;
    }

    @Override
    public T getElement() {
        T item = (T) comboBox.getSelectionModel().getSelectedItem();
        if (item == null) {
            throw new NoSuchElementException("item is now selected");
        }
        return item;
    }
}
