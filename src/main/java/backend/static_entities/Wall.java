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
        if(Board.currentLevel == 1) {
            return Sprite.static_sprites.get("wall");
        }
        else if(Board.currentLevel == 2) {
            return SpriteLv2.static_sprites.get("wall");
        }
        else if(Board.currentLevel == 3) {
            return SpriteLv3.static_sprites.get("wall");
        }
        return null;
    }

    public void updateGameState(GameState gameState) {

    }

    @Override
    public Entity getClone() {
        return new Wall(position);
    }
}
