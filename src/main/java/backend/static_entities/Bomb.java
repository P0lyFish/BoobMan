package main.java.backend.static_entities;

import javafx.scene.image.Image;
import main.java.Board;
import main.java.backend.Entity;
import main.java.backend.GameSound;
import main.java.backend.GameState;
import main.java.backend.agents.BomberMan;
import main.java.backend.static_entities.flames.*;
import main.java.graphics.Sprite;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

public class Bomb extends StaticEntity {
    private final BomberMan bombSetter;
    private float n = 0;
    private int gameTime = 0;
    private int bombImageId = 0;
    private final int time = 100;
    public Bomb(GridPosition position, float timer, BomberMan bombSetter) {
        blocked = false;
        destroyable = true;
        visible = true;
        this.entityType = EntityType.bomb;
        this.timeUntilVanish = timer;
        this.bombSetter = bombSetter;
        this.position = position;
        status = Status.normal;
        n = this.bombSetter.getBlastRange();
        GameSound bomb = new GameSound();
        bomb.playBombFx();
    }

    public void decreaseTimer(float delta) {
        timeUntilVanish -= delta;

    }

    public void updateGameState(GameState gameState) {
        gameTime++;
        int d = gameTime % time;
        if(d < time/3) bombImageId = 0;
        if(time/3 <= d && d < 2*time/3) bombImageId = 1;
        if(d >=2*time/3) bombImageId = 2;
        timeUntilVanish -= (float)1 / GameState.NUM_REFRESH_PER_TIME_UNIT;

        if(timeUntilVanish <= 0) {

            float lenStraightFlame = n-1;
            CenterFlame centerFlame = new CenterFlame();
            GridPosition centerFlamePosition = this.getPosition();
            centerFlame.setPosition(centerFlamePosition);
            gameState.addEntity(centerFlame);
            boolean checkLeft = true;
            boolean checkRight = true;
            boolean checkUp = true;
            boolean checkDown = true;
            for(Entity e : gameState.getEntityList()) {
                if(!e.isDestroyable() && e.getPosition().distance(centerFlamePosition) <=1 && e.getPosition().getX() < centerFlamePosition.getX()) {
                    checkLeft = false;
                }
                if(!e.isDestroyable() && e.getPosition().distance(centerFlamePosition) <=1 && e.getPosition().getX() > centerFlamePosition.getX()) {
                    checkRight = false;
                }
                if(!e.isDestroyable() && e.getPosition().distance(centerFlamePosition) <=1 && e.getPosition().getY() < centerFlamePosition.getY()) {
                    checkUp = false;
                }
                if(!e.isDestroyable() && e.getPosition().distance(centerFlamePosition) <=1 && e.getPosition().getY() > centerFlamePosition.getY()) {
                    checkDown = false;
                }
            }
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


                    if(checkRight) {
                        gameState.addEntity(horizontalFlameRight);
                    }


                    if(checkUp) {
                        gameState.addEntity(verticalFlameUp);
                    }

                    if(checkDown) {
                        gameState.addEntity(verticalFlameDown);

                    }

                    for(Entity e : gameState.getEntityList()) {
                        if(!e.isDestroyable() && e.getPosition().distance(horizontalFlameLeft.getPosition()) <=1 && e.getPosition().getX() < horizontalFlameLeft.getPosition().getX()) {
                            checkLeft = false;
                        }
                        if(!e.isDestroyable() && e.getPosition().distance(horizontalFlameRight.getPosition()) <=1 && e.getPosition().getX() > horizontalFlameRight.getPosition().getX()) {
                            checkRight = false;
                        }
                        if(!e.isDestroyable() && e.getPosition().distance(verticalFlameUp.getPosition()) <=1 && e.getPosition().getY() < verticalFlameUp.getPosition().getY()) {
                            checkUp = false;
                        }
                        if(!e.isDestroyable() && e.getPosition().distance(verticalFlameDown.getPosition()) <=1 && e.getPosition().getY() > verticalFlameDown.getPosition().getY()) {
                            checkDown = false;
                        }
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
            this.bombSetter.setRemainingBombs(this.bombSetter.getRemainingBombs() + 1);
        }

        //set bomb to block when agent get out of bomb
        if(this.bombSetter.getPosition().distance(this.getPosition()) >= 1) {
            boolean checkBomberman = true;
            for(Entity e : gameState.getEntityList()) {
                if(e instanceof BomberMan) {
                    double distance = e.getPosition().distance(this.getPosition());
                    if(distance < 1) {
                        checkBomberman = true;
                        break;
                    }
                    else {
                        checkBomberman = false;
                    }
                }
            }
            if(!checkBomberman){
                this.blocked = true;
            }
        }
    }

    @Override
    public Entity getClone() {
        return new Bomb(position, (float)timeUntilVanish, bombSetter);
    }

    public Image getCurrentTexture() {

        return Sprite.static_sprites.get(String.format("%s_%d", entityType.toString(), bombImageId));
//        if(timeUntilVanish <= REMAINING_TIME_MAX && timeUntilVanish > REMAINING_TIME_MID) {
//        }
//        else if(timeUntilVanish > REMAINING_TIME_MIN && timeUntilVanish <= REMAINING_TIME_MID) {
//            return Sprite.static_sprites.get(String.format("%s_1", entityType.toString()));
//        }
//        else {
//            return Sprite.static_sprites.get(String.format("%s_0", entityType.toString()));
//        }
    }
}