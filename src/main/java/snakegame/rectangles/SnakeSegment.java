package snakegame.rectangles;

public class SnakeSegment extends GameRectangle {

    public SnakeSegment(int posx, int posy) {
        super(posx, posy);
    }

    @Override
    public RectangleType getType() {
        return RectangleType.SNAKE;
    }

}