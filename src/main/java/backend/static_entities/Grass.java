package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.graphics.Sprite;
import main.java.utils.GridPosition;

public class Grass extends Entity {

    public Grass(GridPosition position) {
        this.position = position;
    }

    @Override
    protected Image getCurrentTexture() {
        return Sprite.static_sprites.get("grass");
    }

    @Override
    public void updateGameState(GameState gameState) throws CloneNotSupportedException {

    }

    @Override
    public Entity getClone() {
        return null;
    }
}
