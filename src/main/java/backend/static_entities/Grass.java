package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;
import main.java.graphics.Sprite;

public class Grass extends StaticEntity {

    public Image getCurrentTexture() {
        return Sprite.grass.getCurrentTexture();
    }

    public void updateGameState(GameState gameState) {

    }
}
