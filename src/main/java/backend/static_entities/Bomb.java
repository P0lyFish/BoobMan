package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Agent;
import main.java.backend.agents.BomberMan;
import main.java.backend.static_entities.flames.*;
import main.java.graphics.Sprite;
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
            boolean checkLeft = true;
            boolean checkRight = true;
            boolean checkUp = true;
            boolean checkDown = true;
            if(lenStraightFlame != 1) {
                for(int i = 1;i <= lenStraightFlame;i++) {
                    HorizontalFlame horizontalFlameLeft = new HorizontalFlame();
                    HorizontalFlame horizontalFlameRight = new HorizontalFlame();
                    VerticalFlame verticalFlameUp = new VerticalFlame();
                    VerticalFlame verticalFlameDown = new VerticalFlame();
                    GridPosition infinite = new GridPosition(99999,99999);
                    if(checkLeft) {
                        GridPosition positionLeft = new GridPosition(this.getPosition().getX() - i, this.getPosition().getY());
                        horizontalFlameLeft.setPosition(positionLeft);
                    }
                    else {
                        horizontalFlameLeft.setPosition(infinite);
                    }

                    if(checkRight) {
                        GridPosition positionRight = new GridPosition(this.getPosition().getX() + i, this.getPosition().getY());
                        horizontalFlameRight.setPosition(positionRight);
                    }
                    else {
                        horizontalFlameRight.setPosition(infinite);
                    }

                    if(checkUp) {
                        GridPosition positionUp = new GridPosition(this.getPosition().getX(), this.getPosition().getY() - i);
                        verticalFlameUp.setPosition(positionUp);
                    }
                    else {
                        verticalFlameUp.setPosition(infinite);
                    }

                    if(checkDown) {
                        GridPosition positionDown = new GridPosition(this.getPosition().getX(), this.getPosition().getY() + i);
                        verticalFlameDown.setPosition(positionDown);
                    }
                    else {
                        verticalFlameDown.setPosition(infinite);
                    }

                    for(Entity e : gameState.getEntityList()) {
                        if(e instanceof Wall && e.getPosition().distance(horizontalFlameLeft.getPosition()) < 1) {
                            checkLeft = false;
                        }
                        if(e instanceof Wall && e.getPosition().distance(horizontalFlameRight.getPosition()) < 1) {
                            checkRight = false;
                        }
                        if(e instanceof Wall && e.getPosition().distance(verticalFlameUp.getPosition()) < 1) {
                            checkUp = false;
                        }
                        if(e instanceof Wall && e.getPosition().distance(verticalFlameDown.getPosition()) < 1) {
                            checkDown = false;
                        }

                    }
                    gameState.addEntity(horizontalFlameLeft);
                    gameState.addEntity(horizontalFlameRight);
                    gameState.addEntity(verticalFlameUp);
                    gameState.addEntity(verticalFlameDown);
                }
            }
            GridPosition bombPosition = this.getPosition();
            if(checkRight) {
                HorizontalFlameRight horizontalFlameRight = new HorizontalFlameRight();
                GridPosition lastRight = new GridPosition(bombPosition.getX() + n, bombPosition.getY());
                horizontalFlameRight.setPosition(lastRight);
                gameState.addEntity(horizontalFlameRight);
            }

            if(checkLeft) {
                HorizontalFlameLeft horizontalFlameLeft = new HorizontalFlameLeft();
                GridPosition lastLeft = new GridPosition(bombPosition.getX() - n, bombPosition.getY());
                horizontalFlameLeft.setPosition(lastLeft);
                gameState.addEntity(horizontalFlameLeft);
            }

            if(checkUp) {
                VerticalFlameTop verticalFlameTop = new VerticalFlameTop();
                GridPosition lastTop = new GridPosition(bombPosition.getX(), bombPosition.getY() + n);
                verticalFlameTop.setPosition(lastTop);
                gameState.addEntity(verticalFlameTop);
            }

            if(checkDown) {
                VerticalFlameDown verticalFlameDown = new VerticalFlameDown();
                GridPosition lastDown = new GridPosition(bombPosition.getX(), bombPosition.getY() - n);
                verticalFlameDown.setPosition(lastDown);
                gameState.addEntity(verticalFlameDown);
            }
            gameState.removeEntity(this);
        }
    }

    public Image getCurrentTexture() {
        if(timer <= REMAINING_TIME_MAX && timer > REMAINING_TIME_MID) {
            return Sprite.static_sprites.get(String.format("%s_0", entityType.toString())).getCurrentTexture();
        }
        else if(timer > REMAINING_TIME_MIN && timer <= REMAINING_TIME_MID) {
            return Sprite.static_sprites.get(String.format("%s_1", entityType.toString())).getCurrentTexture();
        }
        else if(timer > 0 && timer <= REMAINING_TIME_MIN) {
            return new Image(String.format("main\\resources\\sprites\\%s_2.png", entityType.toString()));
        }
        else {
            return Sprite.grass.getCurrentTexture();
        }
    }
}
