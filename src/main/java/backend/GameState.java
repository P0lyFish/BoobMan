package main.java.backend;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import main.java.backend.agents.Agent;
import main.java.backend.agents.Balloon;
import main.java.backend.agents.Oneal;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.Bomb;
import main.java.backend.static_entities.Brick;
import main.java.backend.static_entities.Portal;
import main.java.backend.static_entities.Wall;
import main.java.backend.static_entities.items.BombItem;
import main.java.backend.static_entities.items.FlameItem;
import main.java.backend.static_entities.items.SpeedItem;
import main.java.utils.GameStatus;
import main.java.utils.GridPosition;
import main.java.utils.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameState {
    public static final double BOMB_EXPLOSION_TIME = 8;

    public static final int DEFAULT_BLAST_RANGE = 1;

    public static final double DEFAULT_SPEED = 2;

    public static final int DEFAULT_NUM_BOMBS = 1;

    public static final int NUM_REFRESH_PER_TIME_UNIT = 60;

    private GameStatus status = GameStatus.PLAYING;
    private int mapID;

    private List<Entity> entities;
    public Input inputListener;

    public GameState() {
    }

    public GameState(String mapPath, Input inputListener) throws IOException {
        this.inputListener = inputListener;
        entities = new ArrayList<>();

        try {

            File mapFile = new File(mapPath);
            FileReader fin = new FileReader(mapFile);
            BufferedReader reader = new BufferedReader(fin);

            String line;
            int curY = 0;
            while ((line = reader.readLine()) != null) {
                Brick brick = null;
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
                        case 'B':
                            entity = new BombItem(new GridPosition(curX, curY));
                            brick = new Brick(entity.getPosition());
                            break;
                        case 'F':
                            entity = new FlameItem(new GridPosition(curX, curY));
                            brick = new Brick(entity.getPosition());
                            break;
                        case 'S':
                            entity = new SpeedItem(new GridPosition(curX, curY));
                            brick = new Brick(entity.getPosition());
                            break;
                        case 'O':
                            entity = new Portal(new GridPosition(curX, curY));
                            brick = new Brick(entity.getPosition());
                            break;
                    }
                    if (entity != null) {
                        entities.add(entity);
                    }
                    if(brick != null) {
                        entities.add(brick);
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

    public void refresh() {
        if (status != GameStatus.PLAYING) {
            return;
        }
        int n = entities.size();
        for (int i = 0; i < n; i++) {
            try {
                entities.get(i).updateGameState(this);
            } catch (Exception ignored) {
            }
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

    public Agent getPlayerAgent() {
        for (Entity e : entities) {
            if (e instanceof PlayerAgent) {
                return (Agent)e;
            }
        }

        return null;
    }
}