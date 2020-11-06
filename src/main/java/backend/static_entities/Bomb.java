package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.GameState;
import main.java.backend.agents.Agent;
import main.java.backend.agents.BomberMan;
import main.java.backend.static_entities.flames.*;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Bomb extends StaticEntity {
    private float timer;
    private BomberMan bombSetter;


    public Bomb(float timer, BomberMan bombSetter) {
        blocked = true;
        destroyable = false;
        visible = true;
        this.entityType = EntityType.bomb;
        this.timer = timer;
        this.bombSetter = bombSetter;
    }

    public void decreaseTimer(float delta) {
        timer -= delta;

    }

    public void updateGameState(GameState gameState) {
        if(timer < 0) {
            float n = this.bombSetter.getBlastRange();
            float lenStraightFlame = n-1;
            CenterFlame centerFlame = new CenterFlame();
            GridPosition centerFlamePosition = this.getPosition();
            centerFlame.setPosition(centerFlamePosition);
            gameState.addEntity(centerFlame);
            if(lenStraightFlame != 1) {
                for(int i = 1;i <= lenStraightFlame;i++) {
                    HorizontalFlame horizontalFlameUp = new HorizontalFlame();
                    GridPosition positionUp = new GridPosition(this.getPosition().getX(), this.getPosition().getY() + i);
                    horizontalFlameUp.setPosition(positionUp);
                    HorizontalFlame horizontalFlameDown = new HorizontalFlame();
                    GridPosition positionDown = new GridPosition(this.getPosition().getX(), this.getPosition().getY() - i);
                    horizontalFlameDown.setPosition(positionDown);
                    VerticalFlame verticalFlameLeft = new VerticalFlame();
                    GridPosition positionLeft = new GridPosition(this.getPosition().getX() - i, this.getPosition().getY());
                    verticalFlameLeft.setPosition(positionLeft);
                    VerticalFlame verticalFlameRight = new VerticalFlame();
                    GridPosition positionRight = new GridPosition(this.getPosition().getX() + i, this.getPosition().getY());
                    gameState.addEntity(horizontalFlameUp);
                    gameState.addEntity(horizontalFlameDown);
                    gameState.addEntity(verticalFlameLeft);
                    gameState.addEntity(verticalFlameRight);
                }
            }
            GridPosition bombPosition = this.getPosition();
            HorizontalFlameRight horizontalFlameRight = new HorizontalFlameRight();
            HorizontalFlameLeft horizontalFlameLeft = new HorizontalFlameLeft();
            VerticalFlameTop verticalFlameTop = new VerticalFlameTop();
            VerticalFlameDown verticalFlameDown = new VerticalFlameDown();
            GridPosition lastLeft = new GridPosition(bombPosition.getX() - n, bombPosition.getY());
            GridPosition lastRight = new GridPosition(bombPosition.getX() + n, bombPosition.getY());
            GridPosition lastTop = new GridPosition(bombPosition.getX(), bombPosition.getY() + n);
            GridPosition lastDown = new GridPosition(bombPosition.getX(), bombPosition.getY() - n);
            horizontalFlameLeft.setPosition(lastLeft);
            horizontalFlameRight.setPosition(lastRight);
            verticalFlameDown.setPosition(lastDown);
            verticalFlameTop.setPosition(lastTop);
            gameState.addEntity(horizontalFlameLeft);
            gameState.addEntity(horizontalFlameRight);
            gameState.addEntity(verticalFlameDown);
            gameState.addEntity(verticalFlameTop);

            gameState.removeEntity(this);
        }
    }

    public Image getCurrentTexture() {
        if(timer <= REMAINING_TIME_MAX && timer > REMAINING_TIME_MID) {
            return new Image(String.format("main\\resources\\sprites\\%s0.png", entityType.toString()));
        }
        else if(timer > REMAINING_TIME_MIN && timer <= REMAINING_TIME_MID) {
            return new Image(String.format("main\\resources\\sprites\\%s1.png", entityType.toString()));
        }
        else if(timer > 0 && timer <= REMAINING_TIME_MIN) {
            return new Image(String.format("main\\resources\\sprites\\%s2.png", entityType.toString()));
        }
        else {
            return new Image("main\\resources\\sprites\\grass.png");
        }
    }
}
