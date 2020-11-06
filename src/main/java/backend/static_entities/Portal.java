package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.PlayerAgent;
import main.java.utils.EntityType;

public class Portal extends StaticEntity {
    private boolean passed;

    public Portal() {
        this.blocked = false;
        this.destroyable = false;
        this.visible = false;
        this.entityType = EntityType.portal;
        this.passed = false;
    }

    public boolean isOpened() {
        return visible;
    }

    public boolean isPassed() {
        return passed;
    }

    public Image getCurrentTexture() {
        return new Image(String.format("main\\resources\\sprites\\%s.png", entityType.toString()));
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
    }
}
