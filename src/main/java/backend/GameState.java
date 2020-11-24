package main.java.backend;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.backend.agents.Agent;
import main.java.backend.agents.Balloon;
import main.java.backend.agents.Oneal;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.*;
import main.java.backend.static_entities.items.BombItem;
import main.java.backend.static_entities.items.FlameItem;
import main.java.backend.static_entities.items.SpeedItem;
import main.java.utils.GameStatus;
import main.java.utils.GridPosition;
import main.java.utils.Input;
import main.java.utils.KeyCodeSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    public static final double BOMB_EXPLOSION_TIME = 4.8;
    public static final int DEFAULT_BLAST_RANGE = 1;
    public static final double DEFAULT_SPEED = 2;
    public static final int DEFAULT_NUM_BOMBS = 1;
    public static final int NUM_REFRESH_PER_TIME_UNIT = 60;
    public static final int CHANGE_MOVING_TYPE_PERIOD = 30;
    private List<Entity> listGrass = new ArrayList<>();
    private GameStatus status = GameStatus.PLAYING;
    private List<Entity> entities;
    public Input inputListener;
    public static GameSound background;
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
                    Grass grass = new Grass(new GridPosition(curX, curY));
                    listGrass.add(grass);
                    switch (line.charAt(curX)) {
                        case '#':
                            entity = new Wall();
                            entity.setPosition(new GridPosition(curX, curY));
                            break;
                        case '1':
                            KeyCodeSet k = new KeyCodeSet(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT,
                                                          KeyCode.ENTER);
                            entity = new PlayerAgent(new GridPosition(curX, curY), DEFAULT_SPEED,
                                    DEFAULT_BLAST_RANGE, DEFAULT_NUM_BOMBS, k, 1);
                            break;
                        case '2':
                            KeyCodeSet k2 = new KeyCodeSet(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D,
                                    KeyCode.SPACE);
                            entity = new PlayerAgent(new GridPosition(curX, curY), DEFAULT_SPEED,
                                    DEFAULT_BLAST_RANGE, DEFAULT_NUM_BOMBS, k2, 2);
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

                        default:
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
            background = new GameSound();
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
    public List<Entity> getListGrass() {
        return this.listGrass;
    }
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void refresh() {
        if (status != GameStatus.PLAYING) {
            background.clip.stop();
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

    public Agent getPlayerAgent(int playerID) {
        for (Entity e : entities) {
            if (e instanceof PlayerAgent && ((PlayerAgent)e).getPlayerID() == playerID) {
                return (Agent)e;
            }
        }

        return null;
    }

    public GameState getClone() {
        GameState res = new GameState();
        res.setStatus(status);
        for (Entity e : entities) {
            res.addEntity(e.getClone());
        }

        return res;
    }

    public boolean isWin() {
        return status == GameStatus.WIN;
    }

    public boolean isLose() {
        return status == GameStatus.LOSE;
    }
}