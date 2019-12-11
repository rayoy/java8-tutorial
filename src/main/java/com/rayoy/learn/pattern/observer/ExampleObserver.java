package com.rayoy.learn.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class ExampleObserver implements Observer {
    //有被观察者发生变化，自动调用对应观察者的update方法
    @Override
    public void update(Observable object, Object argument) {
        //通过强制类型转换获取被观察者对象
        ExampleObservable example = (ExampleObservable) object;
        System.out.println("example.data changed, the new value of data is " + example.data);
    }
}