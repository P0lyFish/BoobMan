package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.EntityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wall extends StaticEntity {
    public Wall() {
        this.entityType = EntityType.wall;
    }

    public void destroy() {
    }

    public Image getCurrentTexture() {
        String filePath = "src\\main\\resources\\sprites\\" + entityType.toString() +".png";
        try {
            return new Image(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateGameState(GameState gameState) {

    }
}
