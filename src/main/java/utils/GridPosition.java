package main.java.utils;

public class GridPosition {
    static final float EPS = (float) 1e-4;

    private float x;
    private float y;

    public GridPosition(float x, float y) {
        this.x = x;
        this.y = y;

        if (isLatticePoint()) {
            this.x = Math.round(this.x);
            this.y = Math.round(this.y);
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public GridPosition step(Direction dir, float speed) {
        GridPosition newPosition;

        switch (dir) {
            case WEST:
                return new GridPosition(this.getX() - speed, this.getY());
            case NORTH:
                return new GridPosition(this.getX(), this.getY() - speed);
            case EAST:
                return new GridPosition(this.getX() + speed, this.getY());
            case SOUTH:
                return new GridPosition(this.getX(), this.getY() + speed);
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
    }

    public float distance(GridPosition p) {
        return (float) Math.sqrt((x - p.getX()) * (x - p.getX()) + (y - p.getY()) * (y - p.getY()));
    }

    public boolean isLatticePoint() {
        return Math.abs(x - Math.round(x)) <= EPS && Math.abs(y - Math.round(y)) <= EPS;
    }

    @Override
    public String toString() {
        return "GridPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
