package backend.static_entities;

import backend.GameState;

public class Bomb extends StaticEntity {
    private float timer;


    public Bomb(float timer) {
        this.timer = timer;
    }

    public void decreaseTimer(float delta) {
        timer -= delta;
    }

    public void updateGameState(GameState gameState) {

    }
}
