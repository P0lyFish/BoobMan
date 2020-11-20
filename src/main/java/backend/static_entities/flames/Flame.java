package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Agent;
import main.java.backend.static_entities.StaticEntity;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract public class Flame extends StaticEntity {
    public Flame() {
        timeUntilVanish = 1.5;
        status = Status.normal;
    }

    public void updateGameState(GameState gameState) {
        timeUntilVanish -= (float)1 / gameState.NUM_REFRESH_PER_TIME_UNIT;
        for(Entity e : gameState.getEntityList()) {
            if(e.isDestroyable() && e.isVisible() && this.getPosition().distance(e.getPosition()) < 1) {
                e.destroy();
            }
        }
        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
            this.status = Status.normal;
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            this.status = Status.vanishing;
        }
        else{
            this.status = Status.vanished;
            gameState.removeEntity(this);
        }
//        System.out.println(this.timeUntilVanish);
//        System.out.println(this.status.toString());

    }

    public Image getCurrentTexture() {

        if(status == Status.normal) {
            return Sprite.static_sprites.get(String.format("%s2", entityType.toString()));
        }
        else if(status == Status.vanishing) {
            return Sprite.static_sprites.get(String.format("%s1", entityType.toString()));
        }
        return Sprite.static_sprites.get(String.format("%s0", entityType.toString()));
    }
}
//test git