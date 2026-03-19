package snakegame;

public record GameEvent(Type type, Object data) {
    public enum Type {
        SNAKE_MOVED,
        APPLE_SPAWNED,
        GAME_OVER
    }

}
