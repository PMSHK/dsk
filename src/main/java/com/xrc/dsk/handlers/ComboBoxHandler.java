package com.xrc.dsk.handlers;

import javafx.scene.control.ComboBox;

public class ComboBoxHandler <T> extends ElementHandler {
    private ComboBox<?> comboBox;

    public ComboBoxHandler(ComboBox<T> comboBox) {
        super(comboBox);
        this.comboBox = comboBox;
    }

    @Override
    public T getElement() {
        T item = (T) comboBox.getSelectionModel().getSelectedItem();
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        return item;
    }
}
