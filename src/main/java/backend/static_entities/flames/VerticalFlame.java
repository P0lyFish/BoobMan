package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
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
}
