package snakegame;

import org.junit.jupiter.api.Test;

import javax.swing.*;


class GameTest extends JFrame {

    @Test
    void testPlay() {
        Game game = new Game();
        GamePanel panel = new GamePanel();

        add(panel);

        setTitle("Snake Game");
        setSize(610, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

//        game.play();

    }

}