package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.static_entities.flames.Flame;

public class VerticalFlame extends Flame {
    VerticalFlame(float remainingTime) {
        super(remainingTime);
    }

    public Image getCurrentTexture() {
        return new Image("");
    }
}
