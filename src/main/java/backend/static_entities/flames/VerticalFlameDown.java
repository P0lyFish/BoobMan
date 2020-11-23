package main.java.backend.static_entities.flames;

import main.java.backend.Entity;
import main.java.utils.EntityType;

public class VerticalFlameDown extends Flame{
    public VerticalFlameDown() {
        this.entityType = EntityType.explosion_vertical_down_last;
    }

    @Override
    public Entity getClone() {
        VerticalFlameDown a = new VerticalFlameDown();
        a.setPosition(position);
        return a;
    }
}
