package com.xrc.dsk.services;

import com.xrc.dsk.dto.Changeable;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.listeners.Observable;
import com.xrc.dsk.listeners.Observer;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PublisherService  {
    private final WindowDto dto;
    private List<Observer> observers;

//    @Override
//    public void subscribe(Observer observer) {
//        if (observers == null) {
//            observers = new ArrayList<>();
//        }
//        observers.add(observer);
//    }
//
//    @Override
//    public void unsubscribe(Observer observer) {
//        if (observers != null) {
//            observers.remove(observer);
//        }
//    }
//
//    @Override
//    public void notifyListeners() {
//        if (dto != null) {
//            if (dto.changed()) {
//                for (Observer observer : observers) {
//                    observer.update();
//                }
//            }
//        }
//    }
}
