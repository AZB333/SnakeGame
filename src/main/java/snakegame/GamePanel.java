package snakegame;

import snakegame.rectangles.Snake;

import javax.swing.*;

public class GamePanel extends JPanel implements IObserver {

    private Snake snake;

    public GamePanel() {
        EventBus.getInstance().subscribe(this);
    }

    @Override
    public void update(GameEvent event) {
        switch (event.type()) {

            case SNAKE_MOVED:
                this.snake = (Snake) event.data();
                repaint();
                break;

            case GAME_OVER:
                JOptionPane.showMessageDialog(this, "Game Over!");
                break;
        }
    }
}
//    @Override
//    public void paintComponent(Graphics graphics) {
//        super.paintComponent(graphics);
//        setBackground(BACKGROUND_COLOR);
//        drawSnake(graphics);
//    }
