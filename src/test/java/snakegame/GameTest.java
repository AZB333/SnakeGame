package snakegame;

import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    RectangleFactory rectangleFactory = new RectangleFactory();
    Snake snake = new Snake(rectangleFactory);

    @Test
    void appleSpawnsWhenMissing() {
        Game game = new Game(snake, rectangleFactory);
        snake.setApple(null);
        game.update();
        assertNotNull(snake.getApple());
    }
    @Test
    void gameEndsOnCollision() {
        Snake snake = new Snake(rectangleFactory);
        Game game = new Game(snake, rectangleFactory);
        snake.setHeadPosition(0, 0);
        snake.setDirection(Direction.LEFT);

        game.update();
        assertTrue(game.isOver());
    }

}