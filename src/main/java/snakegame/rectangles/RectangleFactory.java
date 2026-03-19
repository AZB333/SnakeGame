package snakegame.rectangles;

public class RectangleFactory {
    public RectangleFactory(){}

    public GameRectangle createApple(int posx, int posy){return new Apple(posx, posy);}
    public GameRectangle createSnakeSegment(int posx, int posy){return new SnakeSegment(posx, posy);}
}
