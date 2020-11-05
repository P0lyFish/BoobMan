package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.static_entities.flames.Flame;

public class CenterFlame extends Flame {
    CenterFlame() {}

    public Image getCurrentTexture() {
        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
            return new Image(".\\image\\sprites\\bomb_exploded2.png");
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            return new Image(".\\image\\sprites\\bomb_exploded1.png");
        }
        else if(timeUntilVanish > 0 && timeUntilVanish <= REMAINING_TIME_MIN) {
            return new Image(".\\image\\sprites\\bomb_exploded.png");
        }
        else {
            return new Image(".\\image\\sprites\\grass.png");
        }
    }
}