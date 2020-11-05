package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.static_entities.flames.Flame;

public class CenterFlame extends Flame {
    CenterFlame(float remainingTime) {
        super(remainingTime);
    }

    public Image getCurrentTexture() {
        return new Image("");
    }
}
