package main.java.backend.static_entities.flames;

import main.java.backend.Entity;
import main.java.utils.EntityType;

public class HorizontalFlameRight extends Flame{
    public HorizontalFlameRight() {
        this.entityType = EntityType.explosion_horizontal_right_last;
    }

    @Override
    public Entity getClone() {
        HorizontalFlameRight a = new HorizontalFlameRight();
        a.setPosition(this.position);
        return a;
    }

}
