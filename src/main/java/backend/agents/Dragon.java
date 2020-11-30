package main.java.backend.agents;

import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.Wall;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Dragon extends Agent {
    public Dragon(GridPosition position, double speed, int score) {
        super(position, speed, score);
        this.entityType = EntityType.dragon;
    }

    @Override
    public Entity getClone() {
        return new Dragon(this.position, this.speed, score);
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
    public GridPosition getPositionForRendering() {
        return new GridPosition(position.getX(), position.getY() - 0.3);
    }

    @Override
    public void updateGameState(GameState gameState) throws CloneNotSupportedException {
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
