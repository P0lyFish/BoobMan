package main.java;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.backend.Entity;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.Grass;
import main.java.backend.static_entities.StaticEntity;
import main.java.backend.static_entities.Wall;
import main.java.utils.GridPosition;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends Application {

    public static final int WIDTH = 21;
    public static final int HEIGHT = 15;
    public static final int DEFAULT_SIZE = 16; // kich thuoc anh
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2; // kich thuoc khi in len man hinh

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> listGrass = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

        testCreateMap();

        render();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void render() throws FileNotFoundException {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        listGrass.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void testCreateMap() throws IOException {

        //testloadLevel
        char[][] map = new char[WIDTH][HEIGHT];
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/levels/Level1.txt"));
        String line;
        int rowNum = 0;
        while ((line = br.readLine()) != null) {
            for(int i = 0; i < line.length(); i++) {
                map[i][rowNum] = line.charAt(i);
            }
            rowNum++;
        }

        Entity object;
        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {
                object = new Grass();
                object.setPosition(new GridPosition(i, j));
                entities.add(object);
                if (Character.compare(map[i][j], '#') == 0) {
                    object = new Wall();
                    object.setPosition(new GridPosition(i, j));
                    entities.add(object);
                }
            }
        }
    }
}
