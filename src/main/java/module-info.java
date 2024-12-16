module com.xrc.dsk {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires static lombok;
    requires static org.slf4j;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.net.http;

    opens com.xrc.dsk to javafx.fxml, org.slf4j;
    opens com.xrc.dsk.controllers to javafx.fxml;
    exports com.xrc.dsk;
    exports com.xrc.dsk.controllers;
}