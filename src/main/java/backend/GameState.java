package main.java.backend;

import javafx.scene.input.KeyEvent;
import main.java.backend.agents.Balloon;
import main.java.backend.agents.Oneal;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.Bomb;
import main.java.backend.static_entities.Brick;
import main.java.backend.static_entities.Portal;
import main.java.backend.static_entities.Wall;
import main.java.utils.GameStatus;
import main.java.utils.GridPosition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameState {
    public static final double BOMB_EXPLOSION_TIME = 8;

    public static final int DEFAULT_BLAST_RANGE = 1;
    public static final int ENHANCED_BLAST_RANGE = 2;

    public static final double DEFAULT_SPEED = 2;
    public static final double ENHANCED_SPEED  = 2.5;

    public static final int DEFAULT_NUM_BOMBS = 1;
    public static final int ENHANCED_NUM_BOMBS = 2;

    public static final int NUM_REFRESH_PER_TIME_UNIT = 60;

    private GameStatus status = GameStatus.PLAYING;
    private int mapID;

    private List<Entity> entities;
    private Stack<KeyEvent> playerInputStack;

    public GameState() {
    }

    public GameState(String mapPath) throws IOException {
        playerInputStack = new Stack<>();

        entities = new ArrayList<>();
        try {
            File mapFile = new File(mapPath);
            FileReader fin = new FileReader(mapFile);
            BufferedReader reader = new BufferedReader(fin);

            String line;
            int curY = 0;
            while ((line = reader.readLine()) != null) {
                for (int curX = 0; curX < line.length(); ++curX) {
                    Entity entity = null;
                    switch (line.charAt(curX)) {
                        case ' ':
                            break;
                        case '#':
                            entity = new Wall();
                            entity.setPosition(new GridPosition(curX, curY));
                            break;
                        case 'p':
                            entity = new PlayerAgent(new GridPosition(curX, curY), DEFAULT_SPEED,
                                    DEFAULT_BLAST_RANGE, DEFAULT_NUM_BOMBS);
                            break;
                        case 'b':
                            entity = new Balloon(new GridPosition(curX, curY), DEFAULT_SPEED);
                            break;
                        case 'o':
                            entity = new Oneal(new GridPosition(curX, curY), DEFAULT_SPEED);
                            break;
                        case '*':
                            entity = new Brick(new GridPosition(curX, curY));
                            break;
                    }
                    if (entity != null) {
                        entities.add(entity);
                    }
                }
                curY += 1;
            }
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
            if (e instanceof Bomb)
                continue;
            e.updateGameState(this);
        }
//        for (Entity e : entities) {
//            e.updateGameState(this);
//        }

        for (Entity e : entities) {
            if (e instanceof Portal && ((Portal) e).isPassed()) {
                status = GameStatus.WIN;
                break;
            }
            if (e instanceof PlayerAgent && ((PlayerAgent) e).isVanished()) {
                status = GameStatus.LOSE;
                break;
            }
        }
    }
}