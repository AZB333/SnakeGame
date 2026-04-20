package snakegame;

import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    RectangleFactory rectangleFactory = new RectangleFactory();
    Snake snake = new Snake(rectangleFactory);

    @Test
    public void testRunningGame(){
        EventBus eventBus = EventBus.getInstance();
        KeyEvent keyEvent = new KeyEvent(
                new Frame(), // The Component that originated the event
                KeyEvent.KEY_PRESSED, // the eventID (KEY_PRESSED, KEY_RELEASED, or KEY_TYPED)
                System.currentTimeMillis(), // timestamp of when the event occurred
                0, // modifiers (we have none)
                KeyEvent.VK_W, // the virtual key code
                'w' //key associated with virtual key
        );
        Game game = new Game(snake, rectangleFactory);
        game.keyPressed(keyEvent);
        assert(game.getSnake().getDirection() == Direction.UP);
    }

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