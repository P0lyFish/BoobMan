package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;
import main.java.graphics.Sprite;
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
            return Sprite.static_sprites.get(String.format("%s", entityType.toString())).getCurrentTexture();
        }
        else if (this.status == Status.vanishing) {
            this.entityType = EntityType.brick_exploded;
            if (timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
                return Sprite.static_sprites.get(String.format("%s0", entityType.toString())).getCurrentTexture();
            } else if (timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
                return Sprite.static_sprites.get(String.format("%s1", entityType.toString())).getCurrentTexture();
            } else if (timeUntilVanish > 0 && timeUntilVanish <= REMAINING_TIME_MIN) {
                return Sprite.static_sprites.get(String.format("%s2", entityType.toString())).getCurrentTexture();
            }
        }
        return Sprite.grass.getCurrentTexture();
    }
}
