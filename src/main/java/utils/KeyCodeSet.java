package main.java.utils;

import javafx.scene.input.KeyCode;

public class KeyCodeSet {
    public KeyCode upKey;
    public KeyCode downKey;
    public KeyCode leftKey;
    public KeyCode rightKey;
    public KeyCode setBombKey;

    public KeyCodeSet(KeyCode upKey, KeyCode downKey, KeyCode leftKey, KeyCode rightKey, KeyCode setBombKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.setBombKey = setBombKey;
    }

    @Override
    public String toString() {
        return "KeyCodeSet{" +
                "upKey=" + upKey +
                ", downKey=" + downKey +
                ", leftKey=" + leftKey +
                ", rightKey=" + rightKey +
                ", setBombKey=" + setBombKey +
                '}';
    }
}
