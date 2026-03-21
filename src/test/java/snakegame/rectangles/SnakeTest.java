package snakegame.rectangles;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;


class SnakeTest {
    private final RectangleFactory rectangleFactory = new RectangleFactory();

    @Test
    void testSnakeConstructor() {
        Snake snake = new Snake(rectangleFactory);

        assert(snake.getScore() == 0);
        assert(Objects.equals(snake.getDirection(), Snake.STARTING_DIRECTION));
    }

    @Test
    void testSnakeMoveRight(){
        Snake snake = new Snake(rectangleFactory);

        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection("right");
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX() - Snake.WINDOW_STRIDE_IN_PIXELS);
        assert(initialHeadY == snake.getHeadPosY());
    }

    @Test
    void testSnakeMoveUp(){
        Snake snake = new Snake(rectangleFactory);
        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection("up");
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX());
        assert(initialHeadY == snake.getHeadPosY() + Snake.WINDOW_STRIDE_IN_PIXELS);
    }

    @Test
    void testSnakeMoveLeft(){
        Snake snake = new Snake(rectangleFactory);
        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection("left");
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX() + Snake.WINDOW_STRIDE_IN_PIXELS);
        assert(initialHeadY == snake.getHeadPosY());
    }

    @Test
    void testSnakeMoveDown(){
        Snake snake = new Snake(rectangleFactory);
        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection("down");
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX());
        assert(initialHeadY == snake.getHeadPosY() - Snake.WINDOW_STRIDE_IN_PIXELS);
    }

    @Test
    void testSnakeCollidesWithApple(){
        Snake snake = new Snake(rectangleFactory);



    }

}