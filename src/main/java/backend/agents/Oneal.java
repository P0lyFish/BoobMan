package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

import java.util.Random;

public class Oneal extends Agent {
    public Oneal(GridPosition position, float speed) {
        super(position, speed);
        entityType = EntityType.oneal;
    }

    public void updateGameState(GameState gameState) {
        if (isVanished()) {
            gameState.removeEntity(this);
            return;
        }

        Direction dir;

        if (position.isLatticePoint()) {
            int pick = new Random().nextInt(Direction.values().length);
            dir = Direction.values()[pick];
        }
        else {
            dir = currentDirection;
        }

        this.move(dir, gameState);

        for (Entity e : gameState.getEntityList()) {
            float d = position.distance(e.getPosition());
            if (d < 1 && e instanceof PlayerAgent) {
                e.destroy();
            }
        }
    }
}
