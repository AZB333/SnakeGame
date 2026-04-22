package snakegame;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class GameWindowTest {
    @Test
    void testGameWindowCreation() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            GameWindow window = new GameWindow();
            assertNotNull(window);
            window.dispose();
        });
    }
}