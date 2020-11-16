package main.java.backend.agents;

import main.java.backend.GameState;
import main.java.backend.static_entities.Bomb;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

abstract public class BomberMan extends Agent {
    protected int blastRange;
    protected int remainingBombs;

    public BomberMan(GridPosition position, double speed, int blastRange) {
        super(position, speed);
        this.blastRange = blastRange;
        entityType = EntityType.bomberman;
    }

    public int getBlastRange() {
        return blastRange;
    }

    public void setBlastRange(int blastRange) {
        this.blastRange = blastRange;
    }

    public int getRemainingBombs() {
        return remainingBombs;
    }

    public void setRemainingBombs(int remainingBombs) {
        this.remainingBombs = remainingBombs;
    }

    public void setBomb(GameState gameState) {
        remainingBombs -= 1;
        gameState.addEntity(new Bomb(position, gameState.BOMB_EXPLOSION_TIME, this));
    }
}