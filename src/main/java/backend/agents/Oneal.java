package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

import java.util.Random;

public class Oneal extends Agent {
    public Oneal(GridPosition position, double speed) {
        super(position, speed);
        entityType = EntityType.oneal;
    }

    public void updateGameState(GameState gameState) {
        if (isVanished() || isVanishing()) {
            gameState.removeEntity(this);
            return;
        }

        decreaseTimeUntilVanish((double)1.0 / GameState.NUM_REFRESH_PER_TIME_UNIT);
        standStill(gameState);
    }
}
