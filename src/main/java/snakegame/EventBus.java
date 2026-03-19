package snakegame;

import java.util.ArrayList;

public class EventBus {

    private static EventBus instance;

    private final ArrayList<IObserver> observers = new ArrayList<>();

    private EventBus() {}

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public void attach(IObserver observer) {
        observers.add(observer);
    }

    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    public void publish(GameEvent event) {
        for (IObserver observer : observers) {
            observer.update(event);
        }
    }
}