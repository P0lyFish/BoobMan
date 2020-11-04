package main.java.backend.agents;

import main.java.backend.GameState;
import main.java.utils.Direction;

import java.util.Random;

public class Oneal extends Agent {
    public Oneal(float speed) {
        super(speed);
    }

    public void updateGameState(GameState gameState) {
        int pick = new Random().nextInt(Direction.values().length);
        Direction randomDir = Direction.values()[pick];

        this.move(randomDir, gameState);
    }
}
