package com.xrc.dsk.panels;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Panel extends Pane implements Initializable {
    private final String path;
    @Getter
    private final FXMLLoader loader;
    @Getter
    private final Node rootNode;

    public Panel(String path) {
        this.path = path;
        loader = new FXMLLoader(getClass().getResource(path));
        loader.setLocation(getClass().getResource(path));
        rootNode = loadRootNode();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private <T extends Pane> T loadRootNode() {
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getController() {
        return loader.getController();
    }

    public abstract void initialize();

}
