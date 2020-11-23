package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Balloon;
import main.java.backend.agents.Oneal;
import main.java.backend.agents.PlayerAgent;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Portal extends StaticEntity {
    private boolean passed;
    private boolean opened;
    public Portal(GridPosition position) {
        this.blocked = true;
        this.destroyable = true;
        this.visible = false;
        this.entityType = EntityType.portal;
        this.passed = false;
        this.opened = false;
        this.position = position;
    }

    //mở khi giết hết quái
    public boolean isOpened() {
        return opened;
    }

    //hiện khi có gạch che bị phá vỡ
    public boolean isVisible(){
        return visible;
    }


    public boolean isPassed() {
        return passed;
    }

    public Image getCurrentTexture() {
        return Sprite.portal.getCurrentTexture();
    }

    public void updateGameState(GameState gameState) {
        for(Entity e : gameState.getEntityList()) {
            if(e instanceof Brick && (e.getStatus() == Status.vanished || e.getStatus() == Status.vanishing) && e.getPosition().distance(this.getPosition()) < 0.3) {
                this.visible = true;
            }
            if(isOpened() && e instanceof PlayerAgent && e.getPosition().distance(this.getPosition()) < 1) {
                this.passed = true;
            }
        }
        //check còn quái ko
        this.opened = true;
        for(Entity e : gameState.getEntityList()) {
            if(e instanceof Balloon || e instanceof Oneal) {
                this.opened = false;
                break;
            }
        }

        if (this.opened) {
            this.blocked = false;
        }
    }

    @Override
    public Entity getClone() {
        return new Portal(position);
    }
}
