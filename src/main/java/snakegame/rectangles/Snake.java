package snakegame.rectangles;

import java.awt.*;
import java.util.ArrayList;

import static snakegame.rectangles.GameRectangle.rec_height;
import static snakegame.rectangles.GameRectangle.rec_width;


public class Snake {

    public static final Color BACKGROUND_COLOR = new Color(43, 86, 137);
    private static final int START_POSITION_X_Y = 250;
    private static final int DEFAULT_SPEED = 25;
    private ArrayList<GameRectangle> body;
    private final RectangleFactory rectangleFactory;

    private String direction;

    private GameRectangle apple;

    public Snake(RectangleFactory rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
        this.body = new ArrayList<>();
        body.add(rectangleFactory.createSnakeSegment(START_POSITION_X_Y, START_POSITION_X_Y));
        GameRectangle head = this.body.getFirst();
        body.add(rectangleFactory.createSnakeSegment(head.getPosx() - rec_width, head.getPosy()));
        GameRectangle behind_head = this.body.get(1);
        body.add(rectangleFactory.createSnakeSegment(behind_head.getPosx() - rec_width, behind_head.getPosy()));

        this.direction = "right";
    }

//    public ArrayList<Rectangle> getBody() {return new ArrayList<>(body); }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return this.direction;
    }

    public void addPart() {
        GameRectangle tail = body.getLast();
        switch (direction) {
            case "right" -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx() - rec_width, tail.getPosy()));
            case "left" -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx() + rec_width, tail.getPosy()));
            case "up" -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx(), tail.getPosy() + rec_width));
            case "down" -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx(), tail.getPosy() - rec_height));
        }
    }

    public boolean checkOutOfBounds(GameRectangle snakeHead){
        int playableWidth = 610; //remove magic number once game structure figured out
        int playableHeight = 610;
        return snakeHead.getPosx() > playableWidth || snakeHead.getPosx() < 0 || snakeHead.getPosy() > playableHeight || snakeHead.getPosy() < 0;
    }
    public boolean isCollision() {
        GameRectangle snakeHead = body.getFirst();

        for (int bodyIndex = 1; bodyIndex < body.size(); bodyIndex++) {
            GameRectangle bodyGameRectangle = body.get(bodyIndex);
            if (snakeHead.intersects(bodyGameRectangle) || checkOutOfBounds(snakeHead)) {
                return true;
            }
        }
        if (apple != null) { //apple collision
            //observer moment
            if(snakeHead.intersects(apple)){
//                EventBus.getInstance().publish("Apple Eaten");
                apple = null;
                this.addPart();
            }
        }
        return false;

    }

    public int getScore(){ return body.size() - 3;}

    public void moveSnake() {

        ArrayList<GameRectangle> movedSnake = new ArrayList<>();

        GameRectangle first = body.getFirst();
        GameRectangle head = rectangleFactory.createSnakeSegment(first.getPosx(), first.getPosy());

        switch (direction) {
            case "right" -> head.setPosx(DEFAULT_SPEED);
            case "left" -> head.setPosx(-DEFAULT_SPEED);
            case "up" -> head.setPosy(-DEFAULT_SPEED);
            case "down" -> head.setPosy(DEFAULT_SPEED);
        }
        movedSnake.add(head);

        for (int i = 1; i < body.size(); i++) {
            GameRectangle previous = body.get(i-1);
            GameRectangle newRec = rectangleFactory.createSnakeSegment(previous.getPosx(), previous.getPosy());
            movedSnake.add(newRec);
        }


        body = movedSnake;
        isCollision();
    }

    public void drawSnake(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        if (apple != null) {
            graphics2D.setPaint(apple.getColor());
            graphics2D.drawRect(apple.getPosx(), apple.getPosy(), rec_width, rec_height);
            graphics2D.fillRect(apple.getPosx(),apple.getPosy(),rec_width,rec_height);
        }

        for (GameRectangle rec: body) {
            graphics2D.setPaint(rec.getColor());
            graphics2D.drawRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
            graphics2D.fillRect(rec.getPosx(),rec.getPosy(),rec_width,rec_height);
        }
    }

    public void setApple(GameRectangle apple) {
        this.apple = apple;
    }

    public GameRectangle getApple() {
        return apple;
    }

}