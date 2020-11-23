package main.java.utils;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.Serializable;
import java.security.Key;
import java.util.BitSet;
import java.util.HashMap;

public class Input implements Serializable {

    /**
     * Bitset which registers if any {@link KeyCode} keeps being pressed or if it is released.
     */
    private final BitSet keyboardBitSet = new BitSet();
    private final HashMap<Integer, Integer> keyboardCounter = new HashMap<Integer, Integer>();

    // -------------------------------------------------
    // default key codes
    // will vary when you let the user customize the key codes or when you add support for a 2nd player
    // -------------------------------------------------

    Scene scene;

    public Input(Scene scene) {
        this.scene = scene;
        addListeners();
    }

    public void addListeners() {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
    }

    public void removeListeners() {
        scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    /**
     * "Key Pressed" handler for all input events: register pressed key in the bitset
     */
    private final EventHandler<KeyEvent> keyPressedEventHandler = event -> {
        // register key down
        Integer code = event.getCode().ordinal();

        keyboardBitSet.set(code, true);

        int newValue = 1;
        if (keyboardCounter.containsKey(code)) {
            newValue = keyboardCounter.get(code) + 1;
        }
        keyboardCounter.put(code, newValue);
    };

    /**
     * "Key Released" handler for all input events: unregister released key in the bitset
     */
    private final EventHandler<KeyEvent> keyReleasedEventHandler = event -> {
        // register key up
        keyboardBitSet.set(event.getCode().ordinal(), false);
    };


    // -------------------------------------------------
    // Evaluate bitset of pressed keys and return the player input.
    // If direction and its opposite direction are pressed simultaneously, then the direction isn't handled.
    // -------------------------------------------------

    public boolean isMoveUp(KeyCodeSet k) {
        return keyboardBitSet.get(k.upKey.ordinal()) && !keyboardBitSet.get(k.downKey.ordinal());
    }

    public boolean isMoveDown(KeyCodeSet k) {
        return keyboardBitSet.get(k.downKey.ordinal()) && !keyboardBitSet.get(k.upKey.ordinal());
    }

    public boolean isMoveLeft(KeyCodeSet k) {
        return keyboardBitSet.get(k.leftKey.ordinal()) && !keyboardBitSet.get(k.rightKey.ordinal());
    }

    public boolean isMoveRight(KeyCodeSet k) {
        return keyboardBitSet.get(k.rightKey.ordinal()) && !keyboardBitSet.get(k.leftKey.ordinal());
    }

    public boolean isSetBomb(KeyCodeSet k) {
        int code = k.setBombKey.ordinal();
        if (keyboardCounter.containsKey(code) && keyboardCounter.get(code) > 0) {
            keyboardCounter.put(code, keyboardCounter.get(code) - 1);
            return true;
        }

        return false;
    }
}