package snakegame.rectangles;

import org.junit.jupiter.api.Test;

import java.awt.*;


class AppleTest {

    @Test
    void testAppleConstructor() {
        int posx = 1;
        int posy = 2;
        Apple apple = new Apple(posx, posy);

        assert(apple.getPosx() == posx);
        assert(apple.getPosy() == posy);
        assert(apple.getColor() == Color.red);
    }

}