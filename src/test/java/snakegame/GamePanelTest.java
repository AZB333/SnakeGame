package snakegame;

import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;


class GamePanelTest {
    @Test
    void testPaintComponentRuns() {
        Game game = new Game(new Snake(new RectangleFactory()), new RectangleFactory());
        GamePanel panel = new GamePanel(game);

        panel.setSize(200, 200);

        // fake graphics using BufferedImage
        Image img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

        panel.paintComponent(g);

        assertNotNull(panel);
    }

    @Test
    void testUpdateSnakeMoved() {
        Game game = new Game(new Snake(new RectangleFactory()), new RectangleFactory());
        GamePanel panel = new GamePanel(game);

        panel.update(GameEvent.SNAKE_MOVED);

        assertNotNull(panel);
    }

}