package snakegame;

import snakegame.rectangles.GameRectangle;
import snakegame.rectangles.Snake;

import javax.swing.*;
import java.awt.*;

import static snakegame.rectangles.GameRectangle.rec_height;
import static snakegame.rectangles.GameRectangle.rec_width;

public class GamePanel extends JPanel implements IObserver {

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
                Snake movedSnake = game.getSnake();
                repaint();
                break;

            case GAME_OVER:
                Snake endSnake = game.getSnake();
                JOptionPane.showMessageDialog(this, "Game Over! Score: " + endSnake.getScore());
                System.exit(0);
                break;
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(SNAKE_BACKGROUND_COLOR);
        if (game != null && game.getSnake() != null) {
            drawSnake(graphics, game.getSnake());
        }
    }

    public void drawSnake(Graphics graphics, Snake snake) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        if (snake.getApple() != null) {
            graphics2D.setPaint(snake.getApple().getColor());
            graphics2D.drawRect(snake.getApple().getPosx(), snake.getApple().getPosy(), rec_width, rec_height);
            graphics2D.fillRect(snake.getApple().getPosx(),snake.getApple().getPosy(),rec_width,rec_height);
        }

        for (GameRectangle rec: snake.getBody()) {
            graphics2D.setPaint(rec.getColor());
            graphics2D.drawRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
            graphics2D.fillRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
        }
    }
}


