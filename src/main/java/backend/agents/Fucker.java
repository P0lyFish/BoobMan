package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.Wall;
import main.java.utils.Direction;
import main.java.utils.GridPosition;

import java.util.List;

public class Fucker extends Agent {
    public Fucker(GridPosition position, double speed) {
        super(position, speed);
    }

    @Override
    public Entity getClone() {
        return new Fucker(this.position, this.speed);
    }

    @Override
    protected boolean isLegalAction(GameState gameState, Direction dir) {
        GridPosition newPosition = position.step(dir, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);

        for (Entity e : gameState.getEntityList()) {
            double d = newPosition.distance(e.getPosition());
            if (d < 1 && (e instanceof Wall)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void updateGameState(GameState gameState) throws CloneNotSupportedException {
        randomMove(gameState);
    }
}
