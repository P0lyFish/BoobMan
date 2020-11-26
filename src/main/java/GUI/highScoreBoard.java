package main.java.GUI;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;

public class highScoreBoard {
    private ImageView background;
    private Label newText;
    private Label Score;
    private highScore yourHighScore;

    public Group createScoreBoard() throws IOException {
        yourHighScore = new highScore();
        background = new ImageView();
        background.setFitHeight(560.0);
        background.setFitWidth(640.0);
        background.setOpacity(0.39);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);
        background.setImage(new Image(new File("res/nen.png").toURI().toString()));
        background.setLayoutX(0);
        background.setLayoutY(0);

        newText = new Label();
        newText.setLayoutX(74.0);
        newText.setLayoutY(79.0);
        newText.setPrefHeight(137.0);
        newText.setPrefWidth(519.0);
        newText.setText("YOUR HIGH SCORE :");
        newText.setTextFill(Paint.valueOf("#ff592b"));
        newText.setFont(Font.font("Arial Bold",50.0));

        Score = new Label();
        Score.setLayoutX(236.0);
        Score.setLayoutY(226.0);
        Score.setPrefHeight(138.0);
        Score.setPrefWidth(230.0);
        Score.setTextFill(Paint.valueOf("#ff1717"));
        Score.setFont(Font.font("Arial Bold",91.0));
        System.out.println(yourHighScore.loadHighScore());
        Score.setText(String.valueOf(yourHighScore.loadHighScore()));

        Group group = new Group();
        group.getChildren().addAll(background,newText,Score);
        return group;
    }
}
