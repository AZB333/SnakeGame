package SnakeGame.rectangles;

public class RectangleFactory {
    public RectangleFactory(){}

    public Rectangle createApple(int posx, int posy){return new Apple(posx, posy);}
    public Rectangle createSnakeSegment(int posx, int posy){return new Rectangle(posx, posy);}
}
