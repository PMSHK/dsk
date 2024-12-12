package com.xrc.dsk.panels;

import com.xrc.dsk.controllers.Controllable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class GeneralPanel implements Initializable{
    private final String path;
    private FXMLLoader loader;

    public GeneralPanel(String path) {
        this.path = path;
        loader = new FXMLLoader(getClass().getResource(path));
        loader.setLocation(getClass().getResource(path));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public  <T extends Pane> T getRootNode(){
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getController(){
        return loader.getController();
    }

    public abstract void initialize();
}
