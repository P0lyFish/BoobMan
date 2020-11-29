package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.Board;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.graphics.Sprite;
import main.java.graphics.SpriteLv2;
import main.java.graphics.SpriteLv3;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Brick extends StaticEntity {
    public Brick(GridPosition position) {
        destroyable = true;
        blocked = true;
        visible = true;
        this.position = position;
        this.entityType = EntityType.brick;
        this.timeUntilVanish = REMAINING_TIME_MAX;
    }

    public Brick() {
        destroyable = true;
        blocked = true;
        visible = true;
        this.entityType = EntityType.brick;
        this.timeUntilVanish = REMAINING_TIME_MAX;
    }

    public void updateGameState(GameState gameState) {
        if(isVanishing()) {

            decreaseTimeUntilVanish((double)1.0 / gameState.NUM_REFRESH_PER_TIME_UNIT);
        }
        if (isVanished()) {
            gameState.removeEntity(this);
        }
    }

    @Override
    public Entity getClone() {
        return new Brick(position);
    }

    public Image getCurrentTexture() {
        if(Board.currentLevel == 1) {
            if (isNormal()) {
                return Sprite.static_sprites.get(String.format("%s", entityType.toString()));
            }
            else {
                this.entityType = EntityType.brick_exploded;
                if (timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
                    return Sprite.static_sprites.get(String.format("%s0", entityType.toString()));
                } else if (timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
                    return Sprite.static_sprites.get(String.format("%s1", entityType.toString()));
                } else {
                    return Sprite.static_sprites.get(String.format("%s2", entityType.toString()));
                }
            }
        }
        if(Board.currentLevel == 2) {
            if (isNormal()) {
                return SpriteLv2.static_sprites.get(String.format("%s", entityType.toString()));
            }
            else {
                this.entityType = EntityType.brick_exploded;
                if (timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
                    return SpriteLv2.static_sprites.get(String.format("%s0", entityType.toString()));
                } else if (timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
                    return SpriteLv2.static_sprites.get(String.format("%s1", entityType.toString()));
                } else {
                    return SpriteLv2.static_sprites.get(String.format("%s2", entityType.toString()));
                }
            }
        }
        if(Board.currentLevel == 3) {
            if (isNormal()) {
                return SpriteLv3.static_sprites.get(String.format("%s", entityType.toString()));
            }
            else {
                this.entityType = EntityType.brick_exploded;
                if (timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
                    return SpriteLv3.static_sprites.get(String.format("%s0", entityType.toString()));
                } else if (timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
                    return SpriteLv3.static_sprites.get(String.format("%s1", entityType.toString()));
                } else {
                    return SpriteLv3.static_sprites.get(String.format("%s2", entityType.toString()));
                }
            }
        }
        return null;

    }
}
