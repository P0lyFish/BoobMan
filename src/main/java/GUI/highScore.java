package main.java.GUI;

import java.io.*;
import java.util.*;

public class highScore {

    public static void saveScore(String score) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("res/highScore.txt"), "utf-8"));
            writer.write(score + "\n");
        } catch (IOException ex) {
            // Report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    public static int loadHighScore() throws IOException {
        FileReader fr = null;
        List<Integer> listScore = new ArrayList<Integer>();
        fr = new FileReader("res/highScore.txt");
        BufferedReader br = new BufferedReader(fr);
        String i;
        while ((i = br.readLine()) != null) {
            int a = Integer.parseInt(i);
            listScore.add(a);
        }
        br.close();
        fr.close();
        Collections.sort(listScore);
        return listScore.get(listScore.size() - 1);
    }

}
