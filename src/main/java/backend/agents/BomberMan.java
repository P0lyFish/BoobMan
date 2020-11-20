package main.java.backend.agents;

import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.static_entities.Bomb;
import main.java.backend.static_entities.flames.Flame;
import main.java.backend.static_entities.items.BombItem;
import main.java.backend.static_entities.items.FlameItem;
import main.java.backend.static_entities.items.SpeedItem;
import main.java.utils.EntityType;
import main.java.utils.GameStatus;
import main.java.utils.GridPosition;

abstract public class BomberMan extends Agent {
    protected int blastRange;
    protected int remainingBombs;

    public BomberMan(GridPosition position, double speed, int blastRange, int numBombs) {
        super(position, speed);
        this.blastRange = blastRange;
        entityType = EntityType.bomberman;
        this.remainingBombs = numBombs;
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
        if (remainingBombs > 0) {
            remainingBombs -= 1;
            gameState.addEntity(new Bomb(position, (float) GameState.BOMB_EXPLOSION_TIME, this));
        }
    }

    protected void interactWithOtherEntities(GameState gameState) {
        for (Entity entity : gameState.getEntityList()) {
            double d = this.getPosition().distance(entity.getPosition());
            if (d > 0.99) {
                continue;
            }

            if (entity != this && entity instanceof Agent) {
                destroy();
                break;
            }

            if (entity instanceof SpeedItem && entity.isVisible()) {
                setSpeed(GameState.ENHANCED_SPEED);
            }
            if (entity instanceof FlameItem && entity.isVisible()) {
                setBlastRange(GameState.ENHANCED_BLAST_RANGE);
            }
            if (entity instanceof BombItem && entity.isVisible()) {
                setRemainingBombs(GameState.ENHANCED_NUM_BOMBS);
            }
        }
    }
}