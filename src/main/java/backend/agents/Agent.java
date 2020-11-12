package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;
import main.java.graphics.Sprite;
import main.java.utils.Direction;
import main.java.utils.GridPosition;

import java.util.Random;

abstract public class Agent extends Entity {
    protected enum MovingType {
        STOP, STEP_LEFT, STEP_RIGHT
    }

    protected float speed;
    protected Direction currentDirection;
    protected MovingType movingType = MovingType.STOP;

    public Agent(GridPosition position, float speed) {
        super(position, true, false, true, REMAINING_TIME_MAX);
        this.speed = speed;
        int pick = new Random().nextInt(Direction.values().length);
        currentDirection = Direction.values()[pick];
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    /**
     *
     * @param dir moving direction
     * @return    is the movement successful
     */
    public boolean move(Direction dir, GameState gameState) {
        GridPosition newPosition = position.step(dir, speed);

        boolean validMove = true;
        for (Entity e : gameState.getEntityList()) {
            float d = newPosition.distance(e.getPosition());
            if (d < 1 && e.isBlocked()) {
                validMove = false;
            }
        }

        if (validMove) {
            this.setPosition(newPosition);
        }

        return validMove;
    }

    public Image getCurrentTexture() {
        if (isNormal()) {
            return Sprite.agent_sprites.get(String.format("%s_%s_%s", entityType.toString(),
                    currentDirection.toString(), movingType.toString())).getCurrentTexture();
        }
        else {
            if (timeUntilVanish > REMAINING_TIME_MID) {
                return Sprite.agent_sprites.get(String.format("%s_VANISHING_1",
                        entityType.toString())).getCurrentTexture();
            }
            if (REMAINING_TIME_MIN <= timeUntilVanish && timeUntilVanish < REMAINING_TIME_MID) {
                return Sprite.agent_sprites.get(String.format("%s_VANISHING_2",
                        entityType.toString())).getCurrentTexture();
            }
            else {
                return Sprite.agent_sprites.get(String.format("%s_VANISHING_3",
                        entityType.toString())).getCurrentTexture();
            }
        }
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}