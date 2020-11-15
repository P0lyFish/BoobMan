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
import main.java.GUI.Taskbar;
import main.java.backend.Entity;
import main.java.backend.GameState;
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
    private GameState gameState;

    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.setLayoutX(0);
        canvas.setLayoutY(80.0);
        Taskbar taskbar = new Taskbar();
        Group group = taskbar.createTaskbar();
        Group root = new Group();
        root.getChildren().addAll(canvas,group);

        Scene scene = new Scene(root, Color.GREEN);

        primaryStage.setScene(scene);
        primaryStage.show();

        gameState = new GameState("src/main/resources/levels/Level1.txt");

        render();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void render() throws FileNotFoundException {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameState.getEntityList().forEach(g -> g.render(gc));
    }
}
