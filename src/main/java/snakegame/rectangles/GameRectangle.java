package snakegame.rectangles;

import java.awt.*;

abstract public class GameRectangle {

    private int posx;
    private int posy;

    public static final int rec_width = 20;
    public static final int rec_height = 20;

    public GameRectangle(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }

    public boolean intersects(GameRectangle obstacle) {
        /*
        return true if x and y coordinates of
        this and obstacle are the same
        */
        return posx == obstacle.getPosx() && posy == obstacle.getPosy();
    }

    public abstract Color getColor();

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosx(int increment) { posx = posx + increment; }

    public void setPosy(int increment) { posy =  posy + increment; }

}