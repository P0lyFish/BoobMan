package main.java.backend.agents;

import javafx.scene.input.KeyEvent;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;
import main.java.utils.Direction;
import main.java.utils.GridPosition;

public class PlayerAgent extends BomberMan {
    public PlayerAgent(float speed, float blastRange) {
        super(speed, blastRange);
    }

    public void updateGameState(GameState gameState) {
        if (position.isLatticePoint()) {
            if (gameState.inputStackIsEmpty()) {
                return;
            }
            KeyEvent playerInput = gameState.popPlayerInputStack();
            switch (playerInput.getCode()) {
                case LEFT:
                    move(Direction.WEST, gameState);
                    currentDirection = Direction.WEST;
                    break;
                case UP:
                    move(Direction.NORTH, gameState);
                    currentDirection = Direction.NORTH;
                    break;
                case RIGHT:
                    move(Direction.EAST, gameState);
                    currentDirection = Direction.EAST;
                    break;
                case DOWN:
                    move(Direction.SOUTH, gameState);
                    currentDirection = Direction.SOUTH;
                    break;
                case SPACE:
                    setBomb(gameState);
            }
        }
        else {
            move(currentDirection, gameState);
        }
    }
}