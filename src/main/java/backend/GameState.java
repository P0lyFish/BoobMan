package main.java.backend;

import java.util.List;

public class GameState {
    private final float UPDATE_PERIOD = 0;

    List<Entity> entities;

    public GameState() {}

    public GameState(String mapPath) {

    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    void takePlayerInput() {

    }

    public void refresh() {
        takePlayerInput();

        for (Entity e : entities) {
            e.updateGameState(this);
        }
    }
}
