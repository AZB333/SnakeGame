package snakegame;

import snakegame.rectangles.Snake;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements IObserver {

    private Snake snake;
    private static final Color SNAKE_BACKGROUND_COLOR = new Color(43, 86, 137);


    public GamePanel() {
        EventBus.getInstance().attach(this);
        setBackground(Color.BLACK);
    }

    @Override
    public void update(GameEvent event) {
        switch (event.type()) {

            case SNAKE_MOVED:
                this.snake = (Snake) event.data();
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
        snake.drawSnake(graphics);
    }
}


