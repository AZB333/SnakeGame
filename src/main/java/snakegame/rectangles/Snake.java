package snakegame.rectangles;

import snakegame.Direction;
import snakegame.GameWindow;

import java.util.ArrayList;

import static snakegame.rectangles.GameRectangle.rec_height;
import static snakegame.rectangles.GameRectangle.rec_width;


public class Snake {

    private static final int START_POSITION_X_Y = 250;
    public static final int WINDOW_STRIDE_IN_PIXELS = 25;
    public static final Direction STARTING_DIRECTION = Direction.RIGHT;
    private static final int STARTING_BODY_SIZE = 3;
    private ArrayList<GameRectangle> body;
    private final RectangleFactory rectangleFactory;

    private Direction direction;

    private GameRectangle apple;

    public Snake(RectangleFactory rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
        this.body = new ArrayList<>();
        initializeBody();

        this.direction = STARTING_DIRECTION; //default direction
    }

    private void initializeBody(){
        body.add(rectangleFactory.createSnakeSegment(START_POSITION_X_Y, START_POSITION_X_Y));
        GameRectangle head = this.body.getFirst();
        body.add(rectangleFactory.createSnakeSegment(head.getPosx() - rec_width, head.getPosy()));
        GameRectangle behind_head = this.body.get(1);
        body.add(rectangleFactory.createSnakeSegment(behind_head.getPosx() - rec_width, behind_head.getPosy()));
    }

    public ArrayList<GameRectangle> getBody() {return new ArrayList<>(body); }

    public int getHeadPosX() {return body.getFirst().getPosx();}

    public int getHeadPosY() {return body.getFirst().getPosy();}


    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return this.direction;
    }

    public void addPart() {
        GameRectangle tail = body.getLast();
        switch (direction) {
            case Direction.RIGHT -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx() - rec_width, tail.getPosy()));
            case Direction.LEFT -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx() + rec_width, tail.getPosy()));
            case Direction.UP -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx(), tail.getPosy() + rec_width));
            case Direction.DOWN -> body.add(rectangleFactory.createSnakeSegment(tail.getPosx(), tail.getPosy() - rec_height));
        }
    }

    public boolean checkOutOfBounds(GameRectangle snakeHead){
        int playableWidth = GameWindow.WINDOW_WIDTH;
        int playableHeight = GameWindow.WINDOW_HEIGHT;
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
            if(snakeHead.intersects(apple)){
                apple = null;
                this.addPart();
            }
        }
        return false;

    }

    public int getScore(){ return body.size() - STARTING_BODY_SIZE;}

    private void adjustDirection(Direction direction, GameRectangle snakeHead){
        switch (direction) {
            case Direction.RIGHT -> snakeHead.incrementPosx(WINDOW_STRIDE_IN_PIXELS);
            case Direction.LEFT -> snakeHead.incrementPosx(-WINDOW_STRIDE_IN_PIXELS);
            case Direction.UP -> snakeHead.incrementPosy(-WINDOW_STRIDE_IN_PIXELS);
            case Direction.DOWN -> snakeHead.incrementPosy(WINDOW_STRIDE_IN_PIXELS);
        }
    }

    public void moveSnake() {

        ArrayList<GameRectangle> movedSnake = new ArrayList<>();

        GameRectangle currHeadSegment = body.getFirst();
        GameRectangle movedHead = rectangleFactory.createSnakeSegment(currHeadSegment.getPosx(), currHeadSegment.getPosy());
        adjustDirection(direction, movedHead);
        movedSnake.add(movedHead);

        for (int i = 1; i < body.size(); i++) {
            GameRectangle previous = body.get(i-1);
            GameRectangle newRec = rectangleFactory.createSnakeSegment(previous.getPosx(), previous.getPosy());
            movedSnake.add(newRec);
        }


        body = movedSnake;
        isCollision();
    }

    public void setHeadPosition(int posx, int posy) {
        body.getFirst().setPosx(posx);
        body.getFirst().setPosy(posy);
    }

    public void setApple(GameRectangle apple) {
        this.apple = apple;
    }

    public GameRectangle getApple() {
        return apple;
    }

}