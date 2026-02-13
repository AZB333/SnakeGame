package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static SnakeGame.Rectangle.rec_height;
import static SnakeGame.Rectangle.rec_width;


public class Snake extends JPanel {

    private static final Color backgroundColor = new Color(43, 86, 137);
    private static final int start = 250;
    private static final int speed = 25;

    private ArrayList<Rectangle> body;

    private String direction;

    private Apple apple;

    private final Game window;


    public Snake(Game window) {
        this.window = window;

        this.body = new ArrayList<>();
        body.add(new Rectangle(start, start));
        Rectangle last = this.body.get(0);
        body.add(new Rectangle(last.getPosx() - rec_width, last.getPosy()));
        Rectangle last_2 = this.body.get(1);
        body.add(new Rectangle(last_2.getPosx() - rec_width, last_2.getPosy()));

        this.direction = "right";
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return this.direction;
    }

    public void addPart() {
        Rectangle last = this.body.getLast();
        switch (this.direction) {
            case "right" -> this.body.add(new Rectangle(last.getPosx() - rec_width, last.getPosy()));
            case "left" -> this.body.add(new Rectangle(last.getPosx() + rec_width, last.getPosy()));
            case "up" -> this.body.add(new Rectangle(last.getPosx() , last.getPosy() + rec_width));
            case "down" -> this.body.add(new Rectangle(last.getPosx(), last.getPosy()  - rec_width));
        }
    }

    public void checkCollision() {
        Rectangle snakeHead = this.body.getFirst();
        for (int i = 1; i < this.body.size(); i++) {
            Rectangle bodyRectangle = this.body.get(i);

            if (snakeHead.intersects(bodyRectangle)) {
                System.out.println("You lose!");
                this.window.setVisible(false);

                JFrame parent = new JFrame("Game over!");
                JOptionPane.showMessageDialog(parent, "Your score: " + this.body.size());

                this.window.dispatchEvent(new WindowEvent(this.window, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
        }

        if (this.apple != null) {
            if (snakeHead.intersects(new Rectangle(this.apple.getPosx(),this.apple.getPosy()))) {
                this.apple = null;
                this.addPart();
            }
        }

    }

    public void moveSnake() {

        ArrayList<Rectangle> movedSnake = new ArrayList<>();

        Rectangle first = this.body.getFirst();
        Rectangle head = new Rectangle(first.getPosx(), first.getPosy());

        switch (this.direction) {
            case "right" -> head.setPosx(speed);
            case "left" -> head.setPosx(-speed);
            case "up" -> head.setPosy(-speed);
            case "down" -> head.setPosy(speed);
        }
        movedSnake.add(head);

        for (int i = 1; i < this.body.size(); i++) {
            Rectangle previous = this.body.get(i-1);
            Rectangle newRec = new Rectangle(previous.getPosx(), previous.getPosy());
            movedSnake.add(newRec);
        }


        this.body = movedSnake;
        checkCollision();
    }

    private void drawSnake(Graphics graphics) {
        moveSnake();

        // draw moved snake
        Graphics2D graphics2D = (Graphics2D) graphics;


        if (this.apple != null) {
            graphics2D.setPaint(Color.red);
            graphics2D.drawRect(this.apple.getPosx(), this.apple.getPosy(), rec_width, rec_height);
            graphics2D.fillRect(this.apple.getPosx(),this.apple.getPosy(),rec_width,rec_height);
        }

        graphics2D.setPaint(Color.blue);
        for (Rectangle rec: this.body) {
            graphics2D.drawRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
            graphics2D.fillRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
        }
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Apple getApple() {
        return this.apple;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(backgroundColor);
        drawSnake(graphics);
    }
}