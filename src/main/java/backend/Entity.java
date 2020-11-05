package main.java.backend;

import javafx.scene.image.Image;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;


public abstract class Entity {
    protected final float REMAINING_TIME_MAX = 2000;
    protected final float REMAINING_TIME_MID = 1300;
    protected final float REMAINING_TIME_MIN = 600;

    protected enum Status {
        normal, vanishing, vanished;
    }

    protected GridPosition position;
    protected boolean visible;
    protected boolean blocked;
    protected boolean destroyable;
    protected Status status = Status.normal;
    protected float timeUntilVanish;
    protected EntityType entityType;

    public Entity() {}

    public Entity(GridPosition position, boolean visible, boolean blocked,
                  boolean destroyable, float timeUntilVanish) {
        this.position = position;
        this.visible = visible;
        this.blocked = blocked;
        this.timeUntilVanish = timeUntilVanish;
    }

    protected void decreaseTimeUntilVanish(float delta) {
        if (status == Status.vanishing) {
            timeUntilVanish -= delta;
        }

        if (timeUntilVanish < 0) {
            status = Status.vanished;
        }
    }

    protected Status getStatus() {
        return status;
    }

    public void destroy() {
        status = Status.vanishing;
    }

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