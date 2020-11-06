package main.java.backend.static_entities.items;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.BomberMan;
import main.java.backend.static_entities.Brick;
import main.java.utils.EntityType;

public class SpeedItem extends Item {
    public SpeedItem() {
        this.entityType = EntityType.speed_item;
    }
    public void updateGameState(GameState gameState) {
        for(Entity e : gameState.getEntityList()) {
            if(e instanceof Brick && (e.getStatus() == Status.vanished || e.getStatus() == Status.vanishing) && e.getPosition().distance(this.getPosition()) < 0.3) {
                this.visible = true;
            }
            if(e instanceof BomberMan && e.getPosition().distance(this.getPosition()) < 1) {
                ((BomberMan) e).setSpeed(((BomberMan) e).getSpeed()+EXTRA_SPEED);
            }
        }
    }

}