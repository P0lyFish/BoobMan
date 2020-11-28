package main.java.backend.agents;


import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

import java.util.Random;


public class Balloon extends Agent {
    public Balloon(GridPosition position, double speed, int score) {
        super(position, speed, score);
        entityType = EntityType.balloon;
    }

    public Balloon getClone() {
        return new Balloon(position, speed, score);
    }

    public void updateGameState(GameState gameState) {
        if (isVanished()) {
            gameState.removeEntity(this);
            gameState.addScore(score);
            return;
        }

        decreaseTimeUntilVanish((double)1.0 / GameState.NUM_REFRESH_PER_TIME_UNIT);
        randomMove(gameState);

        movingType = getStep();
    }
}
