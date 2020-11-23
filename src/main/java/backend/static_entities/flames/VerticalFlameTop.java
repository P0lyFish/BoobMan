package main.java.backend.static_entities.flames;

import main.java.backend.Entity;
import main.java.utils.EntityType;

public class VerticalFlameTop extends Flame {
    public VerticalFlameTop() {
        this.entityType = EntityType.explosion_vertical_top_last;
    }

    @Override
    public Entity getClone() {
        VerticalFlameTop a = new VerticalFlameTop();
        a.setPosition(position);
        return a;
    }
}
