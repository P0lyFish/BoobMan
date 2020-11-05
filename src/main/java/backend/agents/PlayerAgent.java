package main.java.backend.agents;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.Direction;

public class PlayerAgent extends BomberMan {
    private boolean death;

    public PlayerAgent(float speed, float blastRange) {
        super(speed, blastRange);
    }

    public boolean isDeath() {
        return death;
    }

    public Image getCurrentTexture() {
        return new Image("");
    }

    public void updateGameState(GameState gameState) {
        if (getStatus() == Status.vanished) {
            gameState.removeEntity(this);
            return;
        }

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