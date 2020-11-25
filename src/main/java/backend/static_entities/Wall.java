package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wall extends StaticEntity {
    public Wall() {
        this.entityType = EntityType.wall;
        blocked = true;
        this.visible = true;
    }
    public Wall(GridPosition position) {
        this.position = position;
        this.entityType = EntityType.wall;
        blocked = true;
        this.visible = true;
    }

    public void destroy() {
    }

    public Image getCurrentTexture() {
        return Sprite.static_sprites.get("wall");
    }

    public void updateGameState(GameState gameState) {

    }

    @Override
    public Entity getClone() {
        return new Wall(position);
    }
}
