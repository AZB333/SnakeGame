package snakegame;

import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import javax.swing.*;

import java.awt.*;


public class GameWindow extends JFrame {
    public static final int WINDOW_WIDTH = 610;
    public static final int WINDOW_HEIGHT = 610;

    public GameWindow() {
        RectangleFactory rectangleFactory = new RectangleFactory();
        Snake snake = new Snake(rectangleFactory);
        Game game = new Game(snake);
        GamePanel panel = new GamePanel(game);
        add(panel);
        addKeyListener(game);

        setTitle("Snake Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(GameWindow::new);
    }
}