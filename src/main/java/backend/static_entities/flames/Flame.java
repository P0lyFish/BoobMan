package main.java.backend.static_entities.flames;

import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;

abstract public class Flame extends StaticEntity {
    protected float remainingTime;

    public Flame(float remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void updateGameState(GameState gameState) {

    }
}
