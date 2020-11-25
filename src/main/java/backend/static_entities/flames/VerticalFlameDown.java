package main.java.backend.static_entities.flames;

import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.EntityType;

public class VerticalFlameDown extends Flame{
    public VerticalFlameDown() {
        this.entityType = EntityType.explosion_vertical_down_last;
    }

    @Override
    public Entity getClone() {
        VerticalFlameDown a = new VerticalFlameDown();
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
