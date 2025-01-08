package com.xrc.dsk.windows;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static com.xrc.dsk.data.AppParameters.EXTENSION;
import static com.xrc.dsk.data.AppParameters.LOAD_TITLE;
import static com.xrc.dsk.data.AppParameters.SAVE_TITLE;

public class FileManager {
    private final FileChooser fileChooser;
    private final Window window;
    private Set<String> lastFiles;

    public FileManager(Window window) {
        this.window = window;
        this.fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("calculation file","*." + EXTENSION));
    }

    public File openFile() {
        if (lastFiles == null) {
            lastFiles = new HashSet<>();
        }
        fileChooser.setTitle(LOAD_TITLE);
        fileChooser.getInitialDirectory();
        File file = fileChooser.showOpenDialog(window);
        lastFiles.add(file.getAbsolutePath());
        return this.fileChooser.showOpenDialog(window);
    }

    public File saveFile() {
        if (lastFiles == null) {
            lastFiles = new HashSet<>();
        }
        fileChooser.setTitle(SAVE_TITLE);
        File file = fileChooser.showSaveDialog(window);
        fileChooser.setInitialDirectory(file.getParentFile());
        lastFiles.add(file.getAbsolutePath());
        return file;
    }
}
