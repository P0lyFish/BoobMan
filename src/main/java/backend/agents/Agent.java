package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.Brick;
import main.java.backend.static_entities.StaticEntity;
import main.java.backend.static_entities.Wall;
import main.java.graphics.Sprite;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GameStatus;
import main.java.utils.GridPosition;

import java.util.*;

abstract public class Agent extends Entity {
    protected enum MovingType {
        STOP, STEP_LEFT, STEP_RIGHT
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected double speed;
    protected Direction currentDirection;
    protected MovingType movingType = MovingType.STOP;
    protected int movingTimer = 0;

    public Agent(GridPosition position, double speed) {
        super(position, true, false, true, REMAINING_TIME_MAX);
        this.speed = speed;
        int pick = new Random().nextInt(Direction.values().length);
        currentDirection = Direction.values()[pick];
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    protected List<Direction> legalActions(GameState gameState) {
        List<Direction> legalDirs = new ArrayList<>();

        if (isVanishing() || isVanished()) {
            return legalDirs;
        }

        for (Direction dir : Direction.values()) {
            boolean validMove = true;
            GridPosition newPosition = position.step(dir, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
            for (Entity e : gameState.getEntityList()) {
                double d = newPosition.distance(e.getPosition());
                if (d < 1 && e.isBlocked()) {
                    validMove = false;
                    break;
                }
            }

            if (validMove) {
                legalDirs.add(dir);
            }
        }

        return legalDirs;
    }

    public void move(Direction dir, double dist) {
        GridPosition newPosition = position.step(dir, dist);
        setPosition(newPosition);
        setDirection(dir);
    }

    public Image getCurrentTexture() {
        if (isNormal()) {
            return Sprite.agent_sprites.get(String.format("%s_%s_%s", entityType.toString(),
                    currentDirection.toString(), movingType.toString()));
        }
        else {
            if (timeUntilVanish > REMAINING_TIME_MID) {
                return Sprite.agent_sprites.get(String.format("%s_VANISHING_1",
                        entityType.toString()));
            }
            if (REMAINING_TIME_MIN <= timeUntilVanish && timeUntilVanish < REMAINING_TIME_MID) {
                return Sprite.agent_sprites.get(String.format("%s_VANISHING_2",
                        entityType.toString()));
            }
            else {
                return Sprite.agent_sprites.get(String.format("%s_VANISHING_3",
                        entityType.toString()));
            }
        }
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    protected void randomMove(GameState gameState) {
        Direction dir;

        if (position.isLatticePoint()) {
            List<Direction> validActions = legalActions(gameState);
            int pick = new Random().nextInt(validActions.size());
            dir = validActions.get(pick);
        }
        else {
            dir = currentDirection;
        }

        this.move(dir, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);

        for (Entity e : gameState.getEntityList()) {
            double d = position.distance(e.getPosition());
            if (d < 1 && e instanceof PlayerAgent) {
                e.destroy();
            }
        }
    }

    protected void standStill(GameState gameState) {
        for (Entity e : gameState.getEntityList()) {
            double d = position.distance(e.getPosition());
            if (d < 1 && e instanceof PlayerAgent) {
                e.destroy();
            }
        }
    }

    protected void chasePlayer(GameState gameState) throws CloneNotSupportedException {
        if (!position.isLatticePoint()) {
            move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
            return;
        }

        Map<GridPosition, Integer> dist = new HashMap<>();
        Queue<Agent> qu = new LinkedList<>();
        dist.put(gameState.getPlayerAgent().getPosition(), 0);
        qu.add(gameState.getPlayerAgent());

        Agent nxt = null;

        while (!qu.isEmpty() && nxt == null) {
            Agent u = qu.remove();
            // System.out.println(u.getPosition());

            List<Direction> legalMoves = u.legalActions(gameState);
            for (Direction dir : legalMoves) {
                Agent v = (Agent) u.getClone();
                v.move(dir, 1);

                if (!dist.containsKey(v.getPosition())) {
                    dist.put(v.getPosition(), dist.get(u.getPosition()) + 1);
                    qu.add(v);
                    if (v.getPosition().equals(this.position)) {
                        nxt = u;
                        break;
                    }
                }
            }
        }

        if (nxt == null) {
            randomMove(gameState);
            return;
        }

        for (Direction dir : legalActions(gameState)) {
            Agent v = (Agent)this.clone();
            v.move(dir, 1);

            if (v.getPosition().equals(nxt.getPosition())) {
                move(dir, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
                return;
            }
        }
    }
}