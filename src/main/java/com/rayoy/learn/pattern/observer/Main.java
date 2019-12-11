package com.rayoy.learn.pattern.observer;

public class Main {

    public static void main(String[] args) {
        ExampleObservable example = new ExampleObservable();
        example.addObserver(new ExampleObserver());//给example这个被观察者添加观察者，允许添加多個观察者
        example.setData(2);
        example.setData(-5);
        example.setData(9999);

        ObserverA a = new ObserverA();
        ObserverB b = new ObserverB();
        a.addObserver(b);
        b.addObserver(a);

        b.setData(2);
    }
}