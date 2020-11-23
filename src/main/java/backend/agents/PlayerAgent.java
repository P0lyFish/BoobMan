package main.java.backend.agents;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.Direction;
import main.java.utils.GridPosition;
import main.java.utils.Input;

import java.util.List;

public class PlayerAgent extends BomberMan {
    public PlayerAgent(GridPosition position, double speed, int blastRange, int numBombs) {
        super(position, speed, blastRange, numBombs);
    }

    @Override
    public Entity getClone() {
        return new PlayerAgent(position, speed, blastRange, remainingBombs);
    }

    public void updateGameState(GameState gameState) {
        if (isVanished()) {
            gameState.removeEntity(this);
            return;
        }

        decreaseTimeUntilVanish((double)1.0 / GameState.NUM_REFRESH_PER_TIME_UNIT);

        if (position.isLatticePoint()) {
            if (gameState.inputListener.isMoveLeft()) {
                currentDirection = Direction.WEST;
                movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
            }
            else if (gameState.inputListener.isMoveUp()) {
                currentDirection = Direction.NORTH;
                movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
            }
            else if (gameState.inputListener.isMoveRight()) {
                currentDirection = Direction.EAST;
                movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
            }
            else if (gameState.inputListener.isMoveDown()) {
                currentDirection = Direction.SOUTH;
                movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
            }
            else if (gameState.inputListener.isFirePrimaryWeapon()) {
                movingType = MovingType.STOP;
                setBomb(gameState);
            }
            else {
                movingType = MovingType.STOP;
            }

            List<Direction> validActions = legalActions(gameState);
            for (Direction dir : validActions) {
                if (!movingType.equals(MovingType.STOP) && dir.equals(currentDirection)) {
                    move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
                    break;
                }
            }
        }
        else {
            movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
            move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
        }

        interactWithOtherEntities(gameState);
    }
}