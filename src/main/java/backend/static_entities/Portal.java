package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;

public class Portal extends StaticEntity {
    private boolean passed;

    public Portal() {}

    public boolean isOpened() {
        return visible;
    }

    public boolean isPassed() {
        return passed;
    }

    public Image getCurrentTexture() {
        return new Image("");
    }

    public void updateGameState(GameState gameState) {

    }
}
