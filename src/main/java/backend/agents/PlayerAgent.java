package main.java.backend.agents;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.Direction;
import main.java.utils.GridPosition;

public class PlayerAgent extends BomberMan {
    public PlayerAgent(GridPosition position, float speed, int blastRange) {
        super(position, speed, blastRange);
    }

    public void updateGameState(GameState gameState) {
        if (isVanished()) {
            gameState.removeEntity(this);
            return;
        }

        decreaseTimeUntilVanish((float)1.0 / gameState.NUM_REFRESH_PER_TIME_UNIT);

        if (!isVanishing() && position.isLatticePoint()) {
            if (gameState.inputStackIsEmpty()) {
                movingType = MovingType.STOP;
                return;
            }
            KeyEvent playerInput = gameState.popPlayerInputStack();
            switch (playerInput.getCode()) {
                case LEFT:
                    move(Direction.WEST, gameState);
                    currentDirection = Direction.WEST;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case UP:
                    move(Direction.NORTH, gameState);
                    currentDirection = Direction.NORTH;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case RIGHT:
                    move(Direction.EAST, gameState);
                    currentDirection = Direction.EAST;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case DOWN:
                    move(Direction.SOUTH, gameState);
                    currentDirection = Direction.SOUTH;
                    movingType = (movingType == MovingType.STEP_LEFT ? MovingType.STEP_RIGHT : MovingType.STEP_LEFT);
                    break;
                case SPACE:
                    movingType = MovingType.STOP;
                    setBomb(gameState);
            }
        }
        else if (!isVanishing()) {
            move(currentDirection, gameState);
        }

        for (Entity entity : gameState.getEntityList()) {
            float d = this.getPosition().distance(entity.getPosition());
            if (entity != this && entity instanceof Agent && d < 1) {
                destroy();
                break;
            }
            if (entity instanceof Flame && d < 1) {
                destroy();
                break;
            }
        }
    }
}