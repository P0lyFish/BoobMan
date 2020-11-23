package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.EntityType;

public class CenterFlame extends Flame {
    public CenterFlame() {
        this.entityType = EntityType.bomb_exploded;
    }

    @Override
    public Entity getClone() {
        CenterFlame a = new CenterFlame();
        a.setPosition(this.position);
        return a;
    }

}