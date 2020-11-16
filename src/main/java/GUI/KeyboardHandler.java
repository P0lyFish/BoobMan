package main.java.GUI;

import javafx.scene.input.KeyCode;

public class KeyboardHandler {
    private boolean up, down , left, right;

    public KeyboardHandler() {

        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }
    public String move(KeyCode e){
        switch (e) {
            case UP: {
                up = true;
                down = false;
                left = false;
                right = false;
                break;
            }

            case DOWN: {
                up = false;
                down = true;
                left = false;
                right = false;
                break;
            }

            case LEFT: {
                up = false;
                down = false;
                left = true;
                right = false;
                break;
            }

            case RIGHT: {
                up = false;
                down = false;
                left = false;
                right = true;
                break;
            }
            default: {
                up = false;
                down = false;
                left = false;
                right = true;
            }

        }
        if(up) {
            return "UP";
        } else if(down) {
            return "DOWN";
        } else if(left) {
            return "LEFT";
        } else if(right){
            return "RIGHT";
        } else {
            return "NONE";
        }
    }
}
