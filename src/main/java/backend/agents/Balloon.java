package main.java.backend.agents;


import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

import java.util.Random;


public class Balloon extends Agent {
    public Balloon(GridPosition position, double speed) {
        super(position, speed);
        entityType = EntityType.balloon;
    }

    public void updateGameState(GameState gameState) {
        randomMove(gameState);
    }
}
