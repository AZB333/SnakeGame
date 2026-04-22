package snakegame.rectangles;

public class Apple extends GameRectangle {

    public Apple(int posx, int posy) {
        super(posx, posy);
    }

    @Override
    public RectangleType getType() {
        return RectangleType.APPLE;
    }


}