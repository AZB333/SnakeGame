package snakegame;

public interface IGame {

    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers(GameEvent event);
}
