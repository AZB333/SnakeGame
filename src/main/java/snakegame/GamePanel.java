package snakegame;

import snakegame.rectangles.Snake;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements IObserver {

    private Snake snake;
    private static final Color SNAKE_BACKGROUND_COLOR = new Color(43, 86, 137);
    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        EventBus.getInstance().attach(this);
        setBackground(Color.BLACK);
    }

    @Override
    public void update(GameEvent event) {
        switch (event) {

            case SNAKE_MOVED:
                snake = game.getSnake();
                repaint();
                break;

            case GAME_OVER:
                JOptionPane.showMessageDialog(this, "Game Over! Score: " + snake.getScore());
                System.exit(0);
                break;
        }
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(SNAKE_BACKGROUND_COLOR);
        game.getSnake().drawSnake(graphics);
    }
}


