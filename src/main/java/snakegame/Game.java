package snakegame;


import snakegame.rectangles.GameRectangle;
import snakegame.rectangles.RectangleFactory;
import snakegame.rectangles.Snake;

import java.util.Random;

public class Game {

    protected static final int VIRTUAL_RIGHT_KEY_CODE = 39;
    protected static final int VIRTUAL_LEFT_KEY_CODE = 37;
    protected static final int VIRTUAL_UP_KEY_CODE = 38;
    protected static final int VIRTUAL_DOWN_KEY_CODE = 40;
    protected static final int W_KEY_CODE = 87;
    protected static final int A_KEY_CODE = 65;
    protected static final int S_KEY_CODE = 83;
    protected static final int D_KEY_CODE = 68;
    private final RectangleFactory rectangleFactory;
    private final Random random = new Random();
    private final EventBus eventBus = EventBus.getInstance();
    private final Snake snake;

    public Game(Snake snake, RectangleFactory rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
        this.snake = snake;
    }

    public Boolean isOver() {
        boolean over = snake.collisionOccurred();
        if (over) {
            eventBus.publish(GameEvent.GAME_OVER);
        }
        return over;

    }

    public Snake getSnake() {
        return snake;
    }

    public int getRandomPosition() {
        return 25 * random.nextInt(20);
    }

    public void update() {
        if (isOver()) return;

        if (snake.getApple() == null) {
            int x = getRandomPosition();
            int y = getRandomPosition();

            GameRectangle apple = rectangleFactory.createApple(x, y);
            snake.setApple(apple);
            eventBus.publish(GameEvent.APPLE_SPAWNED);
        }

        snake.moveSnake();
        eventBus.publish(GameEvent.SNAKE_MOVED);
    }

    public void handleInput(int keyCode) {
        if ((keyCode == VIRTUAL_RIGHT_KEY_CODE || keyCode == D_KEY_CODE) && !snake.getDirection().equals(Direction.LEFT)) {
            snake.setDirection(Direction.RIGHT); // right arrow pressed
        } else if ((keyCode == VIRTUAL_LEFT_KEY_CODE || keyCode == A_KEY_CODE) && !snake.getDirection().equals(Direction.RIGHT)) {
            snake.setDirection(Direction.LEFT); // left arrow pressed
        } else if ((keyCode == VIRTUAL_UP_KEY_CODE || keyCode == W_KEY_CODE) && !snake.getDirection().equals(Direction.DOWN)) {
            snake.setDirection(Direction.UP); // up arrow pressed
        } else if ((keyCode == VIRTUAL_DOWN_KEY_CODE || keyCode == S_KEY_CODE) && !snake.getDirection().equals(Direction.UP)) {
            snake.setDirection(Direction.DOWN); // down arrow pressed
        }
    }
}