package main.java.backend.static_entities.items;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;

abstract public class Item extends Entity {
    protected final int EXTRA_BOMB = 1;
    protected final int EXTRA_FLAME = 1;
    protected final float EXTRA_SPEED = 1;
    public Image getCurrentTexture() {
       return new Image(String.format("main\\resources\\sprites\\%s.png", entityType.toString()));
    }
}
