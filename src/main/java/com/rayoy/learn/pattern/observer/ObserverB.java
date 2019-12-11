package com.rayoy.learn.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverB extends Observable implements Observer {

    int data = 0;

    @Override
    public void update(Observable object, Object arg) {
        ObserverA observerA = (ObserverA) object;
        System.out.println("ObserverB found that ObserverA changed...");
    }

    public void setData(int data) {
        this.data = data;
        this.setChanged();
        this.notifyObservers();
    }
}