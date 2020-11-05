package main.java.backend;

import javafx.scene.input.KeyEvent;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.Portal;
import main.java.utils.GameStatus;

import java.io.*;
import java.util.List;
import java.util.Stack;

public class GameState {
    public final float BOMB_EXPLOSION_TIME = 0;
    public final float DEFAULT_BLAST_RANGE = 0;
    private final float REMAINING_TIME_MAX = 2000;
    private final float REMAINING_TIME_MID = 1300;
    private final float REMAINING_TIME_MIN = 600;

    private GameStatus status = GameStatus.PLAYING;
    private int mapID;
    private int numRows;
    private int numCols;

    private List<Entity> entities;
    private Stack<KeyEvent> playerInputStack;

    public GameState() {
    }

    public GameState(String mapPath) throws IOException {
        File mapFile = new File(mapPath);
        try {
            FileReader fin = new FileReader(mapFile);
            BufferedReader reader = new BufferedReader(fin);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
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
        if (status != GameStatus.PLAYING) {
            return;
        }

        for (Entity e : entities) {
            e.updateGameState(this);
        }

        for (Entity e : entities) {
            if (e instanceof Portal && ((Portal) e).isPassed()) {
                status = GameStatus.WIN;
                break;
            }
            if (e instanceof PlayerAgent && ((PlayerAgent) e).isDeath()) {
                status = GameStatus.LOSE;
                break;
            }
        }
    }
}