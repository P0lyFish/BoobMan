package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;
import main.java.utils.EntityType;

abstract public class Flame extends StaticEntity {
    public Flame() {}

    public void updateGameState(GameState gameState) {
        for(Entity e : gameState.getEntityList()) {
            if(e.isDestroyable() && e.isVisible() && this.getPosition().distance(e.getPosition()) < 1) {
                e.destroy();
            }
        }

    }

    public Image getCurrentTexture() {
        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
            return new Image(String.format("main\\resources\\sprites\\%s2.png", entityType.toString()));
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            return new Image(String.format("main\\resources\\sprites\\%s1.png", entityType.toString()));
        }
        else if(timeUntilVanish > 0 && timeUntilVanish <= REMAINING_TIME_MIN) {
            return new Image(String.format("main\\resources\\sprites\\%s0.png", entityType.toString()));
        }
        else {
            return new Image("main\\resources\\sprites\\grass.png");
        }
    }
}
