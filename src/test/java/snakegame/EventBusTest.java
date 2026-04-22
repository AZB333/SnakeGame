package snakegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;


class EventBusTest {

    @BeforeEach
    void setup() {
        EventBus.getInstance().clear();
    }

    @Test
    void testEventAttach() {
        EventBus bus = EventBus.getInstance();
        RectangleFactory rectangleFactory = new RectangleFactory();
        Snake snake = new Snake(rectangleFactory);
        Game game = new Game(snake, rectangleFactory);
        GamePanel panel = new GamePanel(game);
        bus.attach(panel);
        bus.detach(panel);


    }
}