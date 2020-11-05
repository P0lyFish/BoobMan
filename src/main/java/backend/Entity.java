package main.java.backend;

import javafx.scene.image.Image;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;


public abstract class Entity {
    protected GridPosition position;
    protected boolean visible;
    protected boolean blocked;

    public Entity() {}

    public GridPosition getPosition() {
        return this.position;
    }

    protected void setPosition(GridPosition position) {
        this.position = position;
    }

    public boolean isVisible() {
        return this.visible;
    }

    protected void setVisible(boolean isVisible) {
        this.visible = isVisible;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    protected void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    abstract protected Image getCurrentTexture();

    abstract public void updateGameState(GameState gameState);
}