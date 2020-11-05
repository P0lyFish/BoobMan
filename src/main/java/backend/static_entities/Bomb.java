package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;
import main.java.backend.agents.Agent;

public class Bomb extends StaticEntity {
    private float timer;
    private Agent bombSetter;


    public Bomb(float timer, Agent bombSetter) {
        this.timer = timer;
        this.bombSetter = bombSetter;
    }

    public void decreaseTimer(float delta) {
        timer -= delta;
    }

    public void updateGameState(GameState gameState) {

    }

    public Image getCurrentTexture() {
        return new Image("");
    }
}
