package main.java.backend.agents;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.Direction;
import main.java.utils.GridPosition;

import java.util.List;

public class PlayerAgent extends BomberMan {
    public PlayerAgent(GridPosition position, double speed, int blastRange, int numBombs) {
        super(position, speed, blastRange, numBombs);
    }

    public void updateGameState(GameState gameState) {
        if (isVanished()) {
            gameState.removeEntity(this);
            return;
        }

        decreaseTimeUntilVanish((double)1.0 / GameState.NUM_REFRESH_PER_TIME_UNIT);

        if (position.isLatticePoint()) {
            if (gameState.inputStackIsEmpty()) {
                movingType = MovingType.STOP;
                return;
            }
            KeyEvent playerInput = gameState.popPlayerInputStack();
            switch (playerInput.getCode()) {
                case LEFT:
                    currentDirection = Direction.WEST;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case UP:
                    currentDirection = Direction.NORTH;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case RIGHT:
                    currentDirection = Direction.EAST;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case DOWN:
                    currentDirection = Direction.SOUTH;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case SPACE:
                    movingType = MovingType.STOP;
                    setBomb(gameState);
            }
            List<Direction> validActions = legalActions(gameState);
            for (Direction dir : validActions) {
                if (!movingType.equals(MovingType.STOP) && dir.equals(currentDirection)) {
                    move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
                }
            }
        }
        else {
            move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
        }

        interactWithOtherEntities(gameState);
    }
}