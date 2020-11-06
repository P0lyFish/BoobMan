package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.EntityType;

public class Wall extends StaticEntity {
    Wall() {
        this.entityType = EntityType.wall;
    }

    public void destroy() {
    }

    public Image getCurrentTexture() {
        return new Image(String.format("main\\resources\\sprites\\%s.png", entityType.toString()));
    }

    public void updateGameState(GameState gameState) {

    }
}
