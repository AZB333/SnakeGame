package snakegame;

import javax.swing.*;

import java.awt.*;

//import static snakegame.Game.WINDOW_HEIGHT;
//import static snakegame.Game.WINDOW_WIDTH;

public class GameWindow extends JFrame {
    public static final int WINDOW_WIDTH = 610;
    public static final int WINDOW_HEIGHT = 610;

    public GameWindow() {
        GamePanel panel = new GamePanel();
        add(panel);
        Game game = new Game();
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