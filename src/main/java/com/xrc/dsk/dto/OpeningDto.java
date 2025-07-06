//package com.xrc.dsk.dto;
//
//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class OpeningDto implements Serializable {
//    private StringProperty name = new SimpleStringProperty();
//    private DoubleProperty thickness = new SimpleDoubleProperty();
//
//    public StringProperty nameProperty() {
//        return name;
//    }
//
//    public DoubleProperty thicknessProperty() {
//        return thickness;
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//    }
//
//    public void setThickness(double thickness) {
//        this.thickness.set(thickness);
//    }
//
//    public String getName() {
//        return name.get();
//    }
//
//    public double getThickness() {
//        return thickness.get();
//    }
//
//}
