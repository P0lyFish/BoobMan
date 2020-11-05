package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;
import main.java.utils.Direction;
import main.java.utils.GridPosition;

import java.util.Random;

abstract public class Agent extends Entity {
    protected float speed;
    protected Direction currentDirection;

    public Agent(float speed) {
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
        return new Image(String.format("src/main/resoures/%s_%s1.png", entityType.toString(),
                currentDirection.toString()));
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}