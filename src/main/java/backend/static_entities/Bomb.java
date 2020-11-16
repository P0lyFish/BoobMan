package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.BomberMan;
import main.java.backend.static_entities.flames.*;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Bomb extends StaticEntity {
    private BomberMan bombSetter;


    public Bomb(GridPosition position, float timer, BomberMan bombSetter) {
        blocked = false;
        destroyable = false;
        visible = true;
        this.entityType = EntityType.bomb;
        this.timeUntilVanish = timer;
        this.bombSetter = bombSetter;
        this.position = position;
        status = Status.normal;
    }

    public void decreaseTimer(float delta) {
        timeUntilVanish -= delta;

    }

    public void updateGameState(GameState gameState) {
        timeUntilVanish -= (float)1 / gameState.NUM_REFRESH_PER_TIME_UNIT;
        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
            this.status = Status.normal;
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            this.status = Status.vanishing;
        }
        if(timeUntilVanish <= 0) {
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
            if(lenStraightFlame != 0) {
                for(int i = 1;i <= lenStraightFlame;i++) {
                    HorizontalFlame horizontalFlameLeft = new HorizontalFlame();
                    HorizontalFlame horizontalFlameRight = new HorizontalFlame();
                    VerticalFlame verticalFlameUp = new VerticalFlame();
                    VerticalFlame verticalFlameDown = new VerticalFlame();
                    GridPosition infinite = new GridPosition(99999,99999);

                    //tạo flame với vị trí tương ứng
                    GridPosition positionLeft = new GridPosition(this.getPosition().getX() - i, this.getPosition().getY());
                    horizontalFlameLeft.setPosition(positionLeft);
                    GridPosition positionRight = new GridPosition(this.getPosition().getX() + i, this.getPosition().getY());
                    horizontalFlameRight.setPosition(positionRight);
                    GridPosition positionUp = new GridPosition(this.getPosition().getX(), this.getPosition().getY() - i);
                    verticalFlameUp.setPosition(positionUp);
                    GridPosition positionDown = new GridPosition(this.getPosition().getX(), this.getPosition().getY() + i);
                    verticalFlameDown.setPosition(positionDown);

                    //kiểm tra xem vị trí đó có phù hợp ko
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
                    if(checkLeft) {
                        gameState.addEntity(horizontalFlameLeft);
                    }
                    else {
                        checkLeft = false;
                    }

                    if(checkRight) {
                        gameState.addEntity(horizontalFlameRight);
                    }
                    else {
                        checkRight = false;
                    }

                    if(checkUp) {
                        gameState.addEntity(verticalFlameUp);
                    }
                    else {
                        checkUp = false;
                    }

                    if(checkDown) {
                        gameState.addEntity(verticalFlameDown);
                    }
                    else {
                        checkDown = false;
                    }


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
                GridPosition lastTop = new GridPosition(bombPosition.getX(), bombPosition.getY() - n);
                verticalFlameTop.setPosition(lastTop);
                gameState.addEntity(verticalFlameTop);
            }

            if(checkDown) {
                VerticalFlameDown verticalFlameDown = new VerticalFlameDown();
                GridPosition lastDown = new GridPosition(bombPosition.getX(), bombPosition.getY() + n);
                verticalFlameDown.setPosition(lastDown);
                gameState.addEntity(verticalFlameDown);
            }
            gameState.removeEntity(this);
        }
    }

    public Image getCurrentTexture() {
        if(this.status == Status.normal) {
            return Sprite.static_sprites.get(String.format("%s_0", entityType.toString()));
        }
        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
            return Sprite.static_sprites.get(String.format("%s_1", entityType.toString()));
        }
        return null;
    }
}