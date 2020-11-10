package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.StaticEntity;
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
        String filePath = "";
        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
            filePath = String.format("main\\resources\\sprites\\%s2.png", entityType.toString());
            try {
                return new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            filePath = String.format("main\\resources\\sprites\\%s1.png", entityType.toString());
            try {
                return new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(timeUntilVanish > 0 && timeUntilVanish <= REMAINING_TIME_MIN) {
            filePath = String.format("main\\resources\\sprites\\%s0.png", entityType.toString());
            try {
                return new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        filePath = "main\\resources\\sprites\\grass.png";
        FileInputStream f = null;
        try {
            f = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Image(f);
    }
}
