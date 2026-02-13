package SnakeGame;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Game extends JFrame implements KeyListener, ActionListener {

    Snake snake;
    private static final int VIRTUAL_RIGHT_KEY_CODE = 39;
    private static final int VIRTUAL_LEFT_KEY_CODE = 37;
    private static final int VIRTUAL_UP_KEY_CODE = 38;
    private static final int VIRTUAL_DOWN_KEY_CODE = 40;
    private static final int W_KEY_CODE = 87;
    private static final int A_KEY_CODE = 65;
    private static final int S_KEY_CODE = 83;
    private static final int D_KEY_CODE = 68;



    public Game() {
        // create the snake
        this.snake = new Snake(this);

        // timer for redrawing the screen
        Timer timer = new Timer(150, this);
        timer.start();

        // timer for drawing apples on the screen
        java.util.Timer drawApples = new java.util.Timer();
        Apple st = new Apple(this.snake);
        drawApples.schedule(st,0,300);

        // window creation & drawing
        add(this.snake);
        setTitle("Snake Game");
        setSize(610, 610);
        this.addKeyListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if ((keyCode == VIRTUAL_RIGHT_KEY_CODE || keyCode == D_KEY_CODE) && !this.snake.getDirection().equals("left")) {
            this.snake.setDirection("right"); // right arrow pressed
        }

        else if ((keyCode == VIRTUAL_LEFT_KEY_CODE || keyCode == A_KEY_CODE) && !this.snake.getDirection().equals("right")) {
            this.snake.setDirection("left"); // left arrow pressed
        }

        else if ((keyCode == VIRTUAL_UP_KEY_CODE || keyCode == W_KEY_CODE) && !this.snake.getDirection().equals("down")) {
            this.snake.setDirection("up"); // up arrow pressed
        }

        else if ((keyCode == VIRTUAL_DOWN_KEY_CODE || keyCode == S_KEY_CODE) && !this.snake.getDirection().equals("up")) {
            this.snake.setDirection("down"); // down arrow pressed
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // redraw the screen
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Game::new);
    }

}