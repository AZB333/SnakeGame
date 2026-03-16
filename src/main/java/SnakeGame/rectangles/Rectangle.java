package SnakeGame.rectangles;


import SnakeGame.IRectangle;

//make this abstract, snake is a list of this, apple is this, make factory for this
public class Rectangle implements IRectangle {

    private int posx;
    private int posy;

    public static final int rec_width = 20;
    public static final int rec_height = 20;

    public Rectangle(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }

    public boolean intersects(Rectangle obstacle) {
        /*
        return true if x and y coordinates of
        this and obstacle are the same
        */
        return posx == obstacle.getPosx() && posy == obstacle.getPosy();
    }


    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosx(int increment) { posx = posx + increment; }

    public void setPosy(int increment) { posy =  posy + increment; }


}