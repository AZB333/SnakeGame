package snakegame.rectangles;

import java.awt.*;

public class Apple extends Rectangle {

    private static final Color color = Color.red;

    public Apple(int posx, int posy) {
        super(posx, posy);
    }

    @Override
    public Color getColor(){return color;}

}