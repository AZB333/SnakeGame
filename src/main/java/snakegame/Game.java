package snakegame;


import snakegame.rectangles.GameRectangle;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

//so far we can implement observer pattern, factory pattern, singleton pattern
//maybe can do snake builder, or have a snake customization window for more factory and builder
//need one more pattern, perchance strategy pattern since apple doesn't need the rest of rectangle
public class Game implements KeyListener, ActionListener {

    private static final int VIRTUAL_RIGHT_KEY_CODE = 39;
    private static final int VIRTUAL_LEFT_KEY_CODE = 37;
    private static final int VIRTUAL_UP_KEY_CODE = 38;
    private static final int VIRTUAL_DOWN_KEY_CODE = 40;
    private static final int W_KEY_CODE = 87;
    private static final int A_KEY_CODE = 65;
    private static final int S_KEY_CODE = 83;
    private static final int D_KEY_CODE = 68;
    private final RectangleFactory rectangleFactory = new RectangleFactory();
    private final Random random = new Random();
    private final EventBus eventBus = EventBus.getInstance();
    private final Snake snake;

    public Game(Snake snake) {
        this.snake = snake;

        // timer for redrawing the screen
        Timer timer = new Timer(200, this);
        timer.start();

    }

    public Boolean isOver() {
        boolean over = snake.isCollision();
        if (over) {
            eventBus.publish(GameEvent.GAME_OVER);
        }
        return over;

    }

    public Snake getSnake() {
        return snake;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isOver()) {
            return;
        }

        if (snake.getApple() == null) {
            int x = getRandomPosition();
            int y = getRandomPosition();

            GameRectangle apple = rectangleFactory.createApple(x, y);
            eventBus.publish(GameEvent.APPLE_SPAWNED);
            snake.setApple(apple);
        }

        snake.moveSnake();
        eventBus.publish(GameEvent.SNAKE_MOVED);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if ((keyCode == VIRTUAL_RIGHT_KEY_CODE || keyCode == D_KEY_CODE) && !snake.getDirection().equals("left")) {
            snake.setDirection("right"); // right arrow pressed
        } else if ((keyCode == VIRTUAL_LEFT_KEY_CODE || keyCode == A_KEY_CODE) && !snake.getDirection().equals("right")) {
            snake.setDirection("left"); // left arrow pressed
        } else if ((keyCode == VIRTUAL_UP_KEY_CODE || keyCode == W_KEY_CODE) && !snake.getDirection().equals("down")) {
            snake.setDirection("up"); // up arrow pressed
        } else if ((keyCode == VIRTUAL_DOWN_KEY_CODE || keyCode == S_KEY_CODE) && !snake.getDirection().equals("up")) {
            snake.setDirection("down"); // down arrow pressed
        }
    }

    public int getRandomPosition() {
        return 25 * random.nextInt(20);
    }
}