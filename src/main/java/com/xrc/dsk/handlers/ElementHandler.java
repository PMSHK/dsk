package com.xrc.dsk.handlers;

import javafx.scene.control.Control;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ElementHandler {
    private final Control control;
    public abstract <T> T getElement();
}
