package main.java.backend;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import main.java.Board;
import main.java.backend.static_entities.Bomb;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;


public abstract class Entity {
    protected static final double REMAINING_TIME_MAX = 1.5;
    protected static final double REMAINING_TIME_MID = (double)1.0;
    protected static final double REMAINING_TIME_MIN = (double)0.5;

    protected enum Status {
        normal, vanishing, vanished;
    }

    protected GridPosition position;
    protected boolean visible;
    protected boolean blocked;
    protected boolean destroyable;
    protected Status status = Status.normal;
    protected double timeUntilVanish;
    protected EntityType entityType;

    public Entity() {}

    public Entity(GridPosition position, boolean visible, boolean blocked,
                  boolean destroyable, double timeUntilVanish) {
        this.position = position;
        this.visible = visible;
        this.blocked = blocked;
        this.destroyable = destroyable;
        this.timeUntilVanish = timeUntilVanish;
    }

    protected void decreaseTimeUntilVanish(double delta) {
        if (status == Status.vanishing) {
            timeUntilVanish -= delta;
        }

        if (timeUntilVanish < 0) {
            status = Status.vanished;
        }
    }

    public double getTimeUntilVanish() {
        return timeUntilVanish;
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

    public void render(GraphicsContext gc) {

        double x = position.getX();
        double y = position.getY();
        // Image img = getCurrentTexture();

        // SnapshotParameters params = new SnapshotParameters();
        // params.setFill(Color.TRANSPARENT);

        // ImageView iv = new ImageView(img);
        // Image base = iv.snapshot(params, null);

        Image base = getCurrentTexture();

        gc.drawImage(base, x * Board.SCALED_SIZE, y * Board.SCALED_SIZE);
    }
}