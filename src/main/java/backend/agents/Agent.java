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
    protected MovingType currentStepType = MovingType.STEP_LEFT;
    protected int currentStepPeriod = 0;

    public Agent(GridPosition position, double speed) {
        super(position, true, false, true, REMAINING_TIME_MAX);

        while (true) {
            this.speed = speed;
            int pick = new Random().nextInt(Direction.values().length);
            currentDirection = Direction.values()[pick];
            if (!currentDirection.equals(Direction.STOP)) {
                break;
            }
        }
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

    protected boolean isLegalAction(GameState gameState, Direction dir) {
        GridPosition newPosition = position.step(dir, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);

        for (Entity e : gameState.getEntityList()) {
            double d = newPosition.distance(e.getPosition());
            if (d < 1 && e.isBlocked()) {
                return false;
            }
        }

        return true;
    }

    protected List<Direction> legalActions(GameState gameState) {
        List<Direction> legalDirs = new ArrayList<>();

        if (isVanishing() || isVanished()) {
            return legalDirs;
        }

        for (Direction dir : Direction.values()) {
            if (isLegalAction(gameState, dir)) {
                legalDirs.add(dir);
            }
        }

        return legalDirs;
    }

    public MovingType getStep() {
        movingTimer += 1;
        if (movingTimer % GameState.CHANGE_MOVING_TYPE_PERIOD == 0) {
            currentStepType = (currentStepType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
            // currentStepPeriod = (currentStepPeriod + 1) % 4;
        }

        // if (currentStepPeriod % 4 == 0 || currentStepPeriod % 4 == 2) {
        //     currentStepType = MovingType.STOP;
        // }
        // else if (currentStepPeriod % 4 == 1) {
        //     currentStepType = MovingType.STEP_LEFT;
        // }
        // else {
        //     currentStepType = MovingType.STEP_RIGHT;
        // }

        return currentStepType;
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

        PlayerAgent player1 = gameState.getPlayerAgent(1);
        PlayerAgent player2 = gameState.getPlayerAgent(2);

        if (player1 == null && player2 == null) {
            randomMove(gameState);
            return;
        }
        // System.out.println(player1.getPosition().distance(position));
        // System.out.println(player2.getPosition().distance(position));

        if (player1 == null) {
            player1 = player2;
        }
        else if (player2 != null && player1.getPosition().distance(position) > player2.getPosition().distance(position)) {
            player1 = player2;
        }

        if (player1.getPosition().distance(position) > GameState.TRACKING_RANGE) {
            randomMove(gameState);
            return;
        }

        dist.put(player1.getPosition(), 0);
        qu.add(player1);

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