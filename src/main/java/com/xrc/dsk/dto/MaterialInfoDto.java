//package com.xrc.dsk.dto;
//
//import javafx.beans.property.FloatProperty;
//import javafx.beans.property.SimpleFloatProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
//@AllArgsConstructor
//@NoArgsConstructor
//public class MaterialInfoDto implements Serializable {
//    private StringProperty name = new SimpleStringProperty();
//    private FloatProperty density = new SimpleFloatProperty();
//
//    public String getName() {
//        return name.get();
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//    }
//
//    public StringProperty nameProperty() {
//        return name;
//    }
//
//    public float getDensity() {
//        return density.get();
//    }
//
//    public void setDensity(float density) {
//        this.density.set(density);
//    }
//
//    public FloatProperty densityProperty() {
//        return density;
//    }
////    private String name;
////    private Float density;
////    private Boolean changed = Boolean.FALSE;
//
////    public void setName(String name) {
////        if (this.name!=null && !this.name.equals(name)) {
////            changed = true;
////        }
////        this.name = name;
////    }
////
////    public void setDensity(Float density) {
////        if (this.density!=null && !this.density.equals(density)) {
////            changed = true;
////        }
////        this.density = density;
////    }
////
////    @Override
////    public boolean filled() {
////        return name != null && density != null;
////    }
////
////    @Override
////    public boolean changed() {
////        Boolean result = changed;
////        changed = false;
////        return result;
////    }
////
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (o == null || getClass() != o.getClass()) return false;
////        MaterialInfoDto that = (MaterialInfoDto) o;
////        return Objects.equals(name, that.name) && Objects.equals(density, that.density);
////    }
////
////    @Override
////    public int hashCode() {
////        return Objects.hash(name, density);
////    }
//}