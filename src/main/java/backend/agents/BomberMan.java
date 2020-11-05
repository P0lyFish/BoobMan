package main.java.backend.agents;

import main.java.backend.GameState;
import main.java.backend.static_entities.Bomb;

abstract public class BomberMan extends Agent {
    protected float blastRange;
    protected int remainingBombs;

    public BomberMan(float speed, float blastRange) {
        super(speed);
        this.blastRange = blastRange;
    }

    public float getBlastRange() {
        return blastRange;
    }

    public void setBlastRange(int blastRange) {
        this.blastRange = blastRange;
    }

    public void setBomb(GameState gameState) {
        remainingBombs -= 1;
        gameState.addEntity(new Bomb(gameState.BOMB_EXPLOSION_TIME, this));
    }
}