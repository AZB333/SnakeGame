package snakegame;

import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InputControllerTest {
    RectangleFactory rectangleFactory = new RectangleFactory();
    Snake snake = new Snake(rectangleFactory);

    KeyEvent keyEvent = new KeyEvent(
            new Frame(), // The Component that originated the event
            KeyEvent.KEY_PRESSED, // the eventID (KEY_PRESSED, KEY_RELEASED, or KEY_TYPED)
            System.currentTimeMillis(), // timestamp of when the event occurred
            0, // modifiers (we have none)
            KeyEvent.VK_W, // the virtual key code
            'w' //key associated with virtual key
    );

    @Test
    public void testRunningGame(){
        Game game = new Game(snake, rectangleFactory);
        InputController inputController = new InputController(game);
        inputController.keyPressed(keyEvent);
        assert(game.getSnake().getDirection() == Direction.UP);
    }

    @Test
    void testKeyReleaseAndKeyTypedDoNothing(){
        Game game = new Game(snake, rectangleFactory);
        InputController inputController = new InputController(game);

        inputController.keyReleased(keyEvent);
        inputController.keyTyped(keyEvent);
    }
}
