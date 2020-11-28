package main.java.backend.agents;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import main.java.backend.Entity;
import main.java.utils.GameSound;
import main.java.backend.GameState;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.*;

import java.util.List;

public class PlayerAgent extends BomberMan {
    private final KeyCodeSet keyCodeSet;
    private final int playerID;
    private boolean screamed = false;
    private int numHearts = 2;
    private final GridPosition startPosition;

    public PlayerAgent(GridPosition position, double speed, int blastRange, int numBombs, KeyCodeSet keyCodeSet,
                       int playerID) {
        super(position, speed, blastRange, numBombs);
        this.startPosition = position;
        this.keyCodeSet = keyCodeSet;
        this.playerID = playerID;
        if (playerID == 1) {
            entityType = EntityType.bomberman1;
        }
        else {
            entityType = EntityType.bomberman2;
        }
    }

    @Override
    public Entity getClone() {
        return new PlayerAgent(position, speed, blastRange, remainingBombs, keyCodeSet, playerID);
    }

    @Override
    public void destroy() {
        if (numHearts == 1) {
            super.destroy();
        }
        else {
            numHearts -= 1;
            setPosition(startPosition);
        }
    }

    public void addExtraLife() {
        numHearts += 1;
    }

    public int getNumHearts() {
        return numHearts;
    }

    public int getPlayerID() {
        return playerID;
    }

    @Override
    public GridPosition getPositionForRendering() {
        return new GridPosition(position.getX(), position.getY() - 0.3);
    }

    public void updateGameState(GameState gameState) {
        decreaseTimeUntilVanish((double)1.0 / GameState.NUM_REFRESH_PER_TIME_UNIT);
        if (isVanished()) {
            gameState.removeEntity(this);
        }

        if (isVanishing() && !screamed) {
            GameSound gameSound = new GameSound();
            gameSound.playBombermanDead();
            screamed = true;
        }

        boolean isMove = true;
        if (position.isLatticePoint()) {
            if (gameState.inputListener.isMoveLeft(keyCodeSet)) {
                currentDirection = Direction.WEST;
                movingType = getStep();
            }
            else if (gameState.inputListener.isMoveUp(keyCodeSet)) {
                currentDirection = Direction.NORTH;
                movingType = getStep();
            }
            else if (gameState.inputListener.isMoveRight(keyCodeSet)) {
                currentDirection = Direction.EAST;
                movingType = getStep();
            }
            else if (gameState.inputListener.isMoveDown(keyCodeSet)) {
                currentDirection = Direction.SOUTH;
                movingType = getStep();
            }
            else if (gameState.inputListener.isSetBomb(keyCodeSet)) {
                movingType = MovingType.STOP;
                setBomb(gameState);
                isMove = false;
            }
            else {
                movingType = MovingType.STOP;
                isMove = false;
            }

            List<Direction> validActions = legalActions(gameState);
            for (Direction dir : validActions) {
                if (isMove && dir.equals(currentDirection)) {
                    move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
                    break;
                }
            }
        }
        else {
            movingType = getStep();
            move(currentDirection, speed / GameState.NUM_REFRESH_PER_TIME_UNIT);
        }

        interactWithOtherEntities(gameState);
    }
}