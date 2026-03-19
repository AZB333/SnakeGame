package snakegame;


import snakegame.rectangles.Rectangle;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

//so far we can implement observer pattern, factory pattern, singleton pattern
//maybe can do snake builder, or have a snake customization window for more factory and builder
//need one more pattern, perchance strategy pattern since apple doesn't need the rest of rectangle
public class Game extends JFrame implements KeyListener, ActionListener, IGame {

    Snake snake;
    private static final int VIRTUAL_RIGHT_KEY_CODE = 39;
    private static final int VIRTUAL_LEFT_KEY_CODE = 37;
    private static final int VIRTUAL_UP_KEY_CODE = 38;
    private static final int VIRTUAL_DOWN_KEY_CODE = 40;
    private static final int W_KEY_CODE = 87;
    private static final int A_KEY_CODE = 65;
    private static final int S_KEY_CODE = 83;
    private static final int D_KEY_CODE = 68;
    public static final int WINDOW_WIDTH = 610;
    public static final int WINDOW_HEIGHT = 610;
    private final RectangleFactory rectangleFactory = new RectangleFactory();
    private final Random random = new Random();
    private final ArrayList<IObserver> observers = new ArrayList<>();


    public Game() {
        // create the snake
        this.snake = new Snake(rectangleFactory);

        // timer for redrawing the screen
        Timer timer = new Timer(200, this);
        timer.start();

        // window creation & drawing
//        add(snake);
        setTitle("Snake Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.addKeyListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Boolean isOver() {
        boolean over = snake.isCollision();
        if(over){
            EventBus.getInstance().publish(new GameEvent(GameEvent.Type.GAME_OVER, snake.getScore()));
//            notifyObservers("Game Over", snake.getScore());
        }
        return over;

    }

    public void play() {

        System.out.println("You lose!");
        setVisible(false);

        JFrame parent = new JFrame("Game over!");
        JOptionPane.showMessageDialog(parent, "Your score: " + (snake.getScore()));

        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }

    public void attach(IObserver observer){
        observers.add(observer);
    }
    public void detach(IObserver observer){
        observers.remove(observer);
    }
    public void notifyObservers(GameEvent gameEvent) {
        for (IObserver observer : observers) {
            observer.update(gameEvent);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if ((keyCode == VIRTUAL_RIGHT_KEY_CODE || keyCode == D_KEY_CODE) && !snake.getDirection().equals("left")) {
            snake.setDirection("right"); // right arrow pressed
        }

        else if ((keyCode == VIRTUAL_LEFT_KEY_CODE || keyCode == A_KEY_CODE) && !snake.getDirection().equals("right")) {
            snake.setDirection("left"); // left arrow pressed
        }

        else if ((keyCode == VIRTUAL_UP_KEY_CODE || keyCode == W_KEY_CODE) && !snake.getDirection().equals("down")) {
            snake.setDirection("up"); // up arrow pressed
        }

        else if ((keyCode == VIRTUAL_DOWN_KEY_CODE || keyCode == S_KEY_CODE) && !snake.getDirection().equals("up")) {
            snake.setDirection("down"); // down arrow pressed
        }
    }

    public int getRandomPosition(){return 25 * random.nextInt(20);}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (isOver()) {
            play();
            return;
        }


        if (snake.getApple() == null) {
            int x = getRandomPosition();
            int y = getRandomPosition();

            Rectangle apple = rectangleFactory.createApple(x, y);
            notifyObservers(new GameEvent(GameEvent.Type.APPLE_SPAWNED, apple));
            snake.setApple(apple);
        }

        snake.moveSnake();
        notifyObservers(new GameEvent(GameEvent.Type.SNAKE_MOVED, snake));
        repaint();
    }

    static void main(String[] args) {
        EventQueue.invokeLater(Game::new);
    }

}