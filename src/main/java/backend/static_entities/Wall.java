package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;

public class Wall extends StaticEntity {
    Wall() {}

    public void destroy() {
    }

    public Image getCurrentTexture() {
        return new Image("");
    }

    public void updateGameState(GameState gameState) {

    }
}
