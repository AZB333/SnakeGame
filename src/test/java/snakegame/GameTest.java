package snakegame;

import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;


class GameTest extends JFrame {
    RectangleFactory rectangleFactory = new RectangleFactory();


    @Test
    void appleSpawnsWhenMissing() {
//        Snake snake = new Snake(rectangleFactory);
//
//        Game game = new Game(snake, rectangleFactory);
//        snake.setApple(null);
//
//        game.update();
//
//        assertNotNull(snake.getApple());
//        assertEquals(100, snake.getApple().getPosx());
//        assertEquals(100, snake.getApple().getPosy());
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