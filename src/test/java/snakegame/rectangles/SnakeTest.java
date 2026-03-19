package snakegame.rectangles;

import org.junit.jupiter.api.Test;

import java.awt.*;


class SnakeTest {

    @Test
    void testSnakeConstructor() {
        RectangleFactory rectangleFactory = new RectangleFactory();
        Snake snake = new Snake(rectangleFactory);
        assert(snake.getScore() == 0);
    }

}