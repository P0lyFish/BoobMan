package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Balloon;
import main.java.backend.agents.Dragon;
import main.java.backend.agents.Oneal;
import main.java.backend.agents.PlayerAgent;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Portal extends StaticEntity {
    private boolean passed;
    private boolean opened;
    private int gameTime = 0;
    private int portalImgId = 0;
    private final int time = 100;
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
        if(isOpened()) {
            return Sprite.static_sprites.get(String.format("portal%d", portalImgId));
        }
        else return Sprite.static_sprites.get("portal1");
    }

    public void updateGameState(GameState gameState) {
        for(Entity e : gameState.getEntityList()) {
            if(e instanceof Brick && (e.getStatus() == Status.vanished || e.getStatus() == Status.vanishing) && e.getPosition().distance(this.getPosition()) == 0) {
                this.visible = true;
                this.destroyable = false;
                this.opened = true;
//                System.out.println("portal open");

            }
            if(isOpened() && e instanceof PlayerAgent && e.getPosition().distance(this.getPosition()) == 0) {
                this.passed = true;
//                System.out.println("pass");
                break;
            }
        }
        //check còn quái ko

        for(Entity e : gameState.getEntityList()) {
            if(e instanceof Balloon || e instanceof Oneal || e instanceof Dragon) {
                this.opened = false;
                break;
            }
        }

        if (this.opened) {
            this.blocked = false;
            gameTime++;
            int d = gameTime % time;
            if(d < time/2) portalImgId = 1;
            else portalImgId = 2;
        }
    }

    @Override
    public Entity getClone() {
        return new Portal(position);
    }
}
