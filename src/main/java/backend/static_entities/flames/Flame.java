package main.java.backend.static_entities.flames;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Agent;
import main.java.backend.static_entities.StaticEntity;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract public class Flame extends StaticEntity {
    public Flame() {
        timeUntilVanish = 1.5;
        status = Status.normal;
        this.visible = true;
    }


    public Image getCurrentTexture() {

        if(status == Status.normal) {
            return Sprite.static_sprites.get(String.format("%s2", entityType.toString()));
        }
        else if(status == Status.vanishing) {
            return Sprite.static_sprites.get(String.format("%s1", entityType.toString()));
        }
        return Sprite.static_sprites.get(String.format("%s0", entityType.toString()));
    }
}
//test git