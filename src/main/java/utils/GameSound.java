package main.java.utils;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class GameSound extends JFrame implements Runnable {
    public Clip clip;
    protected Clip menu;
    protected Clip die;
    private Thread thread;
    public boolean run = true;

    public void playBackgroundFx() {
        if (this.thread == null) {
            run = true;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void playBombFx() {
        try {
            // Open an audio input stream.
            //tiếng bomb
            // create AudioInputStream object
            String filePath = "src/main/resources/sounds/bomb_explode.wav";
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);


//            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMenuSelectFx() {
        try {
            // Open an audio input stream.
            //tiếng bomb
            // create AudioInputStream object
            String filePath = "src/main/resources/sounds/select_menu.wav";
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);


            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMenuFx() {
        try {
            // Open an audio input stream.
            URL url = new File("src/main/resources/sounds/background_play.wav").toURI().toURL();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            menu = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            menu.open(audioIn);
            //while (!clip.isRunning()) {
            menu.start();
            //}
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void playBombermanDead() {
        try {
            URL url = new File("src/main/resources/sounds/background_play.wav").toURI().toURL();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            die = AudioSystem.getClip();
            die.open(audioIn);
            die.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        while (run) {
            try {
                // Open an audio input stream.
                //nhạc nền game play
                URL url = new File("src/main/resources/sounds/background_play.wav").toURI().toURL();
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                // Get a sound clip resource.
                clip = AudioSystem.getClip();
                // Open audio clip and load samples from the audio input stream.
                clip.open(audioIn);
                //while (!clip.isRunning()) {
                clip.start();
                //}
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(91000);
            } catch (InterruptedException e) {
            }

        }
    }

    public static void main(String[] args) {
        GameSound a = new GameSound();
        a.playBackgroundFx();
        GameSound b = new GameSound();
        b.playMenuSelectFx();
    }
}