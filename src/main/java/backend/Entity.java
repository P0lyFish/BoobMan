package backend;

public abstract class Entity {
    protected float coordinateX;
    protected float coordinateY;
    protected boolean isVisible;
    protected boolean isDestroyed;

    public Entity() {}

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public boolean stillAvailable() {
        return !isDestroyed;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public boolean getVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    abstract public void updateGameState(GameState gameState);
}
