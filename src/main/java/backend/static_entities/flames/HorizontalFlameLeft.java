package main.java.backend.static_entities.flames;

import main.java.backend.Entity;
import main.java.utils.EntityType;

public class HorizontalFlameLeft extends Flame {
    public HorizontalFlameLeft() {
        this.entityType = EntityType.explosion_horizontal_left_last;
    }

    @Override
    public Entity getClone() {
        HorizontalFlameLeft a = new HorizontalFlameLeft();
        a.setPosition(this.position);
        return a;
    }

}
