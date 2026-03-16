package SnakeGame.rectangles;

import SnakeGame.EventBus;
import SnakeGame.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static SnakeGame.rectangles.Rectangle.rec_height;
import static SnakeGame.rectangles.Rectangle.rec_width;


public class Snake extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(43, 86, 137);
    private static final int START_POSITION_X_Y = 250;
    private static final int DEFAULT_SPEED = 25;
    private ArrayList<Rectangle> body;
    private final RectangleFactory rectangleFactory;

    private String direction;

    private Rectangle apple;

    public Snake(RectangleFactory rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
        this.body = new ArrayList<>();
        body.add(rectangleFactory.createSnakeBody(START_POSITION_X_Y, START_POSITION_X_Y));
        Rectangle head = this.body.getFirst();
        body.add(rectangleFactory.createSnakeBody(head.getPosx() - rec_width, head.getPosy()));
        Rectangle behind_head = this.body.get(1);
        body.add(rectangleFactory.createSnakeBody(behind_head.getPosx() - rec_width, behind_head.getPosy()));

        this.direction = "right";
    }

    public ArrayList<Rectangle> getBody() {return new ArrayList<>(body); }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return this.direction;
    }

    public void addPart() {
        Rectangle tail = body.getLast();
        switch (direction) {
            case "right" -> body.add(rectangleFactory.createSnakeBody(tail.getPosx() - rec_width, tail.getPosy()));
            case "left" -> body.add(rectangleFactory.createSnakeBody(tail.getPosx() + rec_width, tail.getPosy()));
            case "up" -> body.add(rectangleFactory.createSnakeBody(tail.getPosx(), tail.getPosy() + rec_width));
            case "down" -> body.add(rectangleFactory.createSnakeBody(tail.getPosx(), tail.getPosy() - rec_height));
        }
    }

    public boolean checkOutOfBounds(Rectangle snakeHead){
        int playableWidth = Game.WINDOW_WIDTH - rec_width;
        int playableHeight = Game.WINDOW_HEIGHT - rec_height;
        return snakeHead.getPosx() > playableWidth || snakeHead.getPosx() < 0 || snakeHead.getPosy() > playableHeight || snakeHead.getPosy() < 0;
    }
    public boolean checkCollision() {
        Rectangle snakeHead = body.getFirst();

        for (int bodyIndex = 1; bodyIndex < body.size(); bodyIndex++) {
            Rectangle bodyRectangle = body.get(bodyIndex);
            if (snakeHead.intersects(bodyRectangle) || checkOutOfBounds(snakeHead)) {
                return false;
            }
        }
        if (apple != null) { //apple collision
            //observer moment
            if(snakeHead.intersects(apple)){
                EventBus.getInstance().postMessage("Apple Eaten");
                apple = null;
                this.addPart();
            }
        }
        return true;

    }

    public void moveSnake() {

        ArrayList<Rectangle> movedSnake = new ArrayList<>();

        Rectangle first = body.getFirst();
        Rectangle head = rectangleFactory.createSnakeBody(first.getPosx(), first.getPosy());

        switch (direction) {
            case "right" -> head.setPosx(DEFAULT_SPEED);
            case "left" -> head.setPosx(-DEFAULT_SPEED);
            case "up" -> head.setPosy(-DEFAULT_SPEED);
            case "down" -> head.setPosy(DEFAULT_SPEED);
        }
        movedSnake.add(head);

        for (int i = 1; i < body.size(); i++) {
            Rectangle previous = body.get(i-1);
            Rectangle newRec = rectangleFactory.createSnakeBody(previous.getPosx(), previous.getPosy());
            movedSnake.add(newRec);
        }


        body = movedSnake;
        checkCollision();
    }

    private void drawSnake(Graphics graphics) {
        moveSnake();
        // draw moved snake
        Graphics2D graphics2D = (Graphics2D) graphics;

        if (apple != null) {
            graphics2D.setPaint(Color.red);
            graphics2D.drawRect(apple.getPosx(), apple.getPosy(), rec_width, rec_height);
            graphics2D.fillRect(apple.getPosx(),apple.getPosy(),rec_width,rec_height);
        }

        graphics2D.setPaint(Color.green);
        for (Rectangle rec: body) {
            graphics2D.drawRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
            graphics2D.fillRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
        }
    }

    public void setApple(Rectangle apple) {
        this.apple = apple;
    }

    public Rectangle getApple() {
        return apple;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(BACKGROUND_COLOR);
        drawSnake(graphics);
    }
}