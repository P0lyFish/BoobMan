package main.java.backend.static_entities.flames;

import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.EntityType;

public class VerticalFlameTop extends Flame {
    public VerticalFlameTop() {
        this.entityType = EntityType.explosion_vertical_top_last;
    }

    @Override
    public Entity getClone() {
        VerticalFlameTop a = new VerticalFlameTop();
        a.setPosition(position);
        return a;
    }

    public void updateGameState(GameState gameState) {
        timeUntilVanish -= (float)1 / gameState.NUM_REFRESH_PER_TIME_UNIT;

        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
            this.status = Status.normal;
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            this.status = Status.vanishing;
        }
        else{
            this.status = Status.vanished;
            gameState.removeEntity(this);
        }

    }
}
