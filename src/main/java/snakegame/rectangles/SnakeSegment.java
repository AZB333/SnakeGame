package snakegame.rectangles;

import java.awt.*;

public class SnakeSegment extends Rectangle {

    private static final Color color = Color.green;

    public SnakeSegment(int posx, int posy) {
        super(posx, posy);
    }

    @Override
    public Color getColor(){return color;}

}