package com.xrc.dsk.services;

import com.xrc.dsk.converters.JsonConverter;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.panels.CalculationPanel;
import com.xrc.dsk.windows.FileManager;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SaveLoader {
    private DataStorage dataStorage = DataStorage.getInstance();
    private WindowDto dto;
    private JsonConverter jsonConverter;
    private FileManager fileManager;
    private final Window window;

    public SaveLoader(Window window) {
        this.window = window;
    }

    public void load(WindowDto dto) {
        File file = fileManager.openFile();
        Path path = Paths.get(file.getAbsolutePath());
        if (!Files.exists(path)) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
        try {
            dataStorage.getInstance();
            dataStorage.setWindowDto(jsonConverter.fromJson(Files.readString(path), dto.getClass()));
//
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
if (fileManager == null) {
    fileManager = new FileManager(window);
}
if (jsonConverter == null) {
    jsonConverter = new JsonConverter();
}
        File file = fileManager.saveFile();
        Path path = Paths.get(file.getAbsolutePath());
        String value = jsonConverter.toJson(dataStorage.getWindowDto());
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            try (FileLock lock = channel.lock()) {
                ByteBuffer buffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8));
                channel.write(buffer);
                System.out.println("file written successfully");
            } catch (IOException e) {
                System.out.println("File is busy now: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public List<CalculationPanel> getPanels() {
        List<CalculationPanel> panels = new ArrayList<CalculationPanel>();
        return panels;
    }
}
