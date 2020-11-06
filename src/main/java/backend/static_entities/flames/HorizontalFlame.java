package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.EntityType;

public class HorizontalFlame extends Flame {
    public HorizontalFlame() {
        this.entityType = EntityType.explosion_horizontal;
    }

}
