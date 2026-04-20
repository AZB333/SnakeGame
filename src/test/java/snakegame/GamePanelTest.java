package snakegame;

import org.junit.jupiter.api.Test;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;


class GamePanelTest {
    RectangleFactory rectangleFactory = new RectangleFactory();
    Snake snake = new Snake(rectangleFactory);

    @Test
    void testEventBus(){

    }

}