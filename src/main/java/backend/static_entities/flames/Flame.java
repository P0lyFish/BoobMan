package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
            return Sprite.static_sprites.get(String.format("%s2", entityType.toString())).getCurrentTexture();
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            return Sprite.static_sprites.get(String.format("%s1", entityType.toString())).getCurrentTexture();

        }
        else if(timeUntilVanish > 0 && timeUntilVanish <= REMAINING_TIME_MIN) {
            return Sprite.static_sprites.get(String.format("%s0", entityType.toString())).getCurrentTexture();

        }

        return Sprite.grass.getCurrentTexture();
    }
}
