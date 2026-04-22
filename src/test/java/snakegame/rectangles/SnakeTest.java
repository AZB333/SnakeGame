package snakegame.rectangles;

import org.junit.jupiter.api.Test;
import snakegame.Direction;

import java.awt.*;
import java.util.Objects;


class SnakeTest {
    private final RectangleFactory rectangleFactory = new RectangleFactory();

    @Test
    void testSnakeConstructor() {
        Snake snake = new Snake(rectangleFactory);

        assert(snake.getScore() == 0);
        assert(Objects.equals(snake.getDirection(), Snake.STARTING_DIRECTION));
        assert(snake.getBody().size() == Snake.STARTING_BODY_SIZE);
    }

    @Test
    void testSnakeMoveRight(){
        Snake snake = new Snake(rectangleFactory);

        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection(Direction.RIGHT);
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX() - Snake.WINDOW_STRIDE_IN_PIXELS);
        assert(initialHeadY == snake.getHeadPosY());
    }

    @Test
    void testSnakeMoveUp(){
        Snake snake = new Snake(rectangleFactory);
        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection(Direction.UP);
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX());
        assert(initialHeadY == snake.getHeadPosY() + Snake.WINDOW_STRIDE_IN_PIXELS);
    }

    @Test
    void testSnakeMoveLeft(){
        Snake snake = new Snake(rectangleFactory);
        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection(Direction.LEFT);
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX() + Snake.WINDOW_STRIDE_IN_PIXELS);
        assert(initialHeadY == snake.getHeadPosY());
    }

    @Test
    void testSnakeMoveDown(){
        Snake snake = new Snake(rectangleFactory);
        int initialHeadX = snake.getHeadPosX();
        int initialHeadY = snake.getHeadPosY();

        snake.setDirection(Direction.DOWN);
        snake.moveSnake();

        assert(initialHeadX == snake.getHeadPosX());
        assert(initialHeadY == snake.getHeadPosY() - Snake.WINDOW_STRIDE_IN_PIXELS);
    }

    @Test
    void testAddPartAddsToScore(){
        Snake snake = new Snake(rectangleFactory);
        assert(snake.getScore() == 0);
        snake.addPart();
        assert(snake.getScore() == 1);
    }

    @Test
    void testSnakeColor(){
        Snake snake = new Snake(rectangleFactory);
        GameRectangle head = snake.getBody().getFirst();
        assert(head.getType() == RectangleType.SNAKE);
    }
}