package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;
import main.java.utils.EntityType;

public class Brick extends StaticEntity {
    public Brick() {
        destroyable = true;
        blocked = true;
        visible = true;
        this.entityType = EntityType.brick;
    }

    public void updateGameState(GameState gameState) {

    }

    public Image getCurrentTexture() {
        if (this.status == Status.normal) {
            return new Image(String.format("main\\resources\\sprites\\%s.png", entityType.toString()));
        }
        else if (this.status == Status.vanishing) {
            this.entityType = EntityType.brick_exploded;
            if (timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
                return new Image(String.format("main\\resources\\sprites\\%s0.png", entityType.toString()));
            } else if (timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
                return new Image(String.format("main\\resources\\sprites\\%s1.png", entityType.toString()));
            } else if (timeUntilVanish > 0 && timeUntilVanish <= REMAINING_TIME_MIN) {
                return new Image(String.format("main\\resources\\sprites\\%s2.png", entityType.toString()));
            }
        }
        return new Image("main\\resources\\sprites\\grass.png");
    }
}
