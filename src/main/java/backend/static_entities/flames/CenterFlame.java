package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.BomberMan;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.EntityType;

public class CenterFlame extends Flame {
    public CenterFlame() {
        this.entityType = EntityType.bomb_exploded;
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
            if(e.isDestroyable() && e.isVisible() && this.getPosition().distance(e.getPosition()) <= 1) {
                e.destroy();
            }
            else if(e instanceof PlayerAgent && this.getPosition().distance(e.getPosition()) <= 1.3 && (Math.abs(this.getPosition().getY() - e.getPosition().getY()) < 0.3 || this.getPosition().getX() == e.getPosition().getX())) {
                e.destroy();
            }
        }
    }
    @Override
    public Entity getClone() {
        CenterFlame a = new CenterFlame();
        a.setPosition(position);
        return a;
    }

}