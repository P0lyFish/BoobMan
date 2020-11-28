package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.Board;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.graphics.Sprite;
import main.java.graphics.SpriteLv2;
import main.java.graphics.SpriteLv3;
import main.java.utils.GridPosition;

public class Grass extends Entity {

    public Grass(GridPosition position) {
        this.position = position;
    }

    @Override
    protected Image getCurrentTexture() {
        if(Board.level == 1) {
            return Sprite.static_sprites.get("grass");
        }
        else if(Board.level == 2) {
            return SpriteLv2.static_sprites.get("grass");
        }
        else if(Board.level == 3) {
            return SpriteLv3.static_sprites.get("grass");
        }
        return null;
    }

    @Override
    public void updateGameState(GameState gameState) throws CloneNotSupportedException {

    }

    @Override
    public Entity getClone() {
        return null;
    }
}
