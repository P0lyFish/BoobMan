package main.java.backend;

import javafx.scene.image.Image;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;


public abstract class Entity {
    protected static final float REMAINING_TIME_MAX = 2000;
    protected static final float REMAINING_TIME_MID = 1300;
    protected static final float REMAINING_TIME_MIN = 600;

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

    public Status getStatus() {
        return status;
    }

    public boolean isNormal() {
        return status == Status.normal;
    }

    public boolean isVanishing() {
        return status == Status.vanishing;
    }

    public boolean isVanished() {
        return status == Status.vanished;
    }

    public void destroy() {
        status = Status.vanishing;
    }

    public GridPosition getPosition() {
        return this.position;
    }

    public void setPosition(GridPosition position) {
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

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isDestroyable() {
        return this.destroyable;
    }

    protected void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }
    abstract protected Image getCurrentTexture();

    abstract public void updateGameState(GameState gameState);
}