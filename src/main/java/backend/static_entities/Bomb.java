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
        if(timer <= REMAINING_TIME_MAX && timer > REMAINING_TIME_MID) {
            return new Image(".\\image\\sprites\\bomb.png");
        }
        else if(timer > REMAINING_TIME_MIN && timer <= REMAINING_TIME_MID) {
            return new Image(".\\image\\sprites\\bomb_1.png");
        }
        else if(timer > 0 && timer <= REMAINING_TIME_MIN) {
            return new Image(".\\image\\sprites\\bomb_2.png");
        }
        else {
            return new Image(".\\image\\sprites\\grass.png");
        }
    }
}
