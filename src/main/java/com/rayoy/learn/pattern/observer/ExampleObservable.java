package com.rayoy.learn.pattern.observer;

import java.util.Observable;

public class ExampleObservable extends Observable {
    int data = 0;

    public void setData(int data) {
        this.data = data;
        this.setChanged();//标记此 Observable对象为已改变的对象
        this.notifyObservers();//通知所有的观察者
    }
}