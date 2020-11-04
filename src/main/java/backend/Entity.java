package main.java.backend;

import main.java.utils.GridPosition;

public abstract class Entity {
    protected GridPosition position;
    protected boolean isVisible;

    public Entity() {}

    public GridPosition getPosition() {
        return this.position;
    }

    public void setPosition(GridPosition position) {
        this.position = position;
    }

    public float getPositionX() {
        return position.getX();
    }

    public void setPositionX(int coordinateX) {
        position.setX(coordinateX);
    }

    public float getPositionY() {
        return position.getY();
    }

    public void setPositionY(float coordinateY) {
        position.setY(coordinateY);
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    abstract public void updateGameState(GameState gameState);
}
