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
    requires org.slf4j;
    requires ch.qos.logback.classic;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.google.common;
    requires java.desktop;

    opens com.xrc.dsk to javafx.fxml, org.slf4j, com.fasterxml.jackson.databind;
    opens com.xrc.dsk.dto to com.fasterxml.jackson.databind;
    opens com.xrc.dsk.controllers to javafx.fxml;
    opens com.xrc.dsk.handlers.material to com.google.common;
    exports com.xrc.dsk;
    exports com.xrc.dsk.controllers;
    exports com.xrc.dsk.dto;
    exports com.xrc.dsk.listeners;
    exports com.xrc.dsk.events;
    exports com.xrc.dsk.dto.medicine;
    exports com.xrc.dsk.viewModels;
    exports com.xrc.dsk.handlers.material;
    opens com.xrc.dsk.dto.medicine to com.fasterxml.jackson.databind;

}