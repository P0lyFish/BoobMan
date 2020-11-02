package main.java.backend.agents;

import backend.Entity;
import backend.GameState;
import utils.Direction;

abstract public class Agent extends Entity {
    protected float speed;
    protected Direction currentDirection;

    public Agent() {}

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
