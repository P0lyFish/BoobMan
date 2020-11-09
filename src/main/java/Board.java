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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Board extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 15;
    public static final int DEFAULT_SIZE = 16;
    private static final int TRANSPARENT_COLOR = 0xffffffff;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();

    public void render() throws FileNotFoundException {
        // clear
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Grass
        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {
                Image img = new Image(new FileInputStream("src\\main\\resources\\sprites\\grass.png"));
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                ImageView iv = new ImageView(img);
                Image base = iv.snapshot(params, null);
                gc.drawImage(base, i * DEFAULT_SIZE, j * DEFAULT_SIZE);
            }
        }

        // List Entities
        entities.forEach(g -> g.render(gc));
    }

    public void testCreateMap() {
        //testloadLevel

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(DEFAULT_SIZE * WIDTH, DEFAULT_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    render();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
