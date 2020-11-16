package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Balloon;
import main.java.backend.agents.Oneal;
import main.java.backend.agents.PlayerAgent;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;

public class Portal extends StaticEntity {
    private boolean passed;
    private boolean opened;
    public Portal() {
        this.blocked = false;
        this.destroyable = false;
        this.visible = false;
        this.entityType = EntityType.portal;
        this.passed = false;
        this.opened = false;
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
            if(e instanceof PlayerAgent && e.getPosition().distance(this.getPosition()) < 1) {
                this.passed = true;
            }
        }
        //check còn quái ko
        for(Entity e : gameState.getEntityList()) {
            if(e instanceof Balloon || e instanceof Oneal) {
                this.opened = false;
                break;
            }
            else this.opened = true;
        }
    }
}
