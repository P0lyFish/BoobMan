package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.EntityType;

public class VerticalFlame extends Flame {
    public VerticalFlame() {
        this.entityType = EntityType.explosion_vertical;
    }

    @Override
    public Entity getClone() {
        VerticalFlame a = new VerticalFlame();
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
        for(Entity e : gameState.getEntityList()) {
            if(e.isDestroyable() && e.isVisible() && this.getPosition().distance(e.getPosition()) <= 1 && this.getPosition().getX() == e.getPosition().getX()) {
                e.destroy();
            }
            else if(e instanceof PlayerAgent && this.getPosition().distance(e.getPosition()) <= 1.3 && (Math.abs(this.getPosition().getX() - e.getPosition().getX())  == 0)) {
                e.destroy();
            }
        }
    }
}
