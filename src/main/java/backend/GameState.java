package main.java.backend;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GameState {
    public final float BOMB_EXPLOSION_TIME = 0;
    public final float DEFAULT_BLAST_RANGE = 0;

    List<Entity> entities;
    Stack<KeyEvent> playerInputStack;

    public GameState() {}

    public GameState(String mapPath) {}

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public List<Entity> getEntityList() {
        return this.entities;
    }

    public void addPlayerInput(KeyEvent key) {
        while (!playerInputStack.empty()) {
            playerInputStack.pop();
        }
        playerInputStack.add(key);
    }

    public boolean inputStackIsEmpty() {
        return playerInputStack.empty();
    }

    public KeyEvent popPlayerInputStack() {
        return playerInputStack.pop();
    }

    public void refresh() {
        for (Entity e : entities) {
            e.updateGameState(this);
        }
    }
}