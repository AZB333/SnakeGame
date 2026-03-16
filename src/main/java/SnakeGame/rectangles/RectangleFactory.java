package SnakeGame.rectangles;

import SnakeGame.IRectangle;

public class RectangleFactory {
    public RectangleFactory(){}

    public Rectangle createApple(Snake snake){return new Apple(snake);}
    public Rectangle createSnakeBody(int posx, int posy){return new Rectangle(posx, posy);}
}
