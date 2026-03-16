package SnakeGame;

import java.util.ArrayList;
import java.util.List;

public class EventBus { // implements IGame {
    private static final EventBus instance = new EventBus();
//    private static final List<IGameObserver> observers = new ArrayList<>();

    private EventBus(){}

    public static EventBus getInstance(){
        return instance;
    }

    public void postMessage(String message){
//        for(IGameObserver observer : observers){
//            observer.update(message);
//        }
    }
//
//    public void attach(IGameObserver observer) {
//        observers.add(observer);
//    }
//
//    public  void detach(IGameObserver observer) {
//        observers.remove(observer);
//    }

}
