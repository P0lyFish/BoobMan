package main.java.GUI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


import java.io.File;

public class Taskbar {
    private Label timer;
    private Label score;
    private ImageView background;

    public Group createTaskbar() {
        Image image = new Image(new File("res/background.png").toURI().toString());

        background = new ImageView();
        background.setLayoutY(0);
        background.setLayoutY(0);
        background.setFitWidth(672.0);
        background.setFitHeight(80.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);
        background.setImage(image);

        timer = new Label();
        timer.setLayoutX(234.0);
        timer.setLayoutY(22.0);
        timer.setPrefHeight(35.0);
        timer.setPrefWidth(62.0);
        timer.setText("180");
        timer.setTextFill(Paint.valueOf("#fdfafa"));
        timer.setFont(Font.font("Calibri Bold",32.0));

        score = new Label();
        score.setLayoutY(21.0);
        score.setLayoutX(567.0);
        score.setPrefWidth(62.0);
        score.setPrefHeight(22.0);
        score.setText("100");
        score.setTextFill(Paint.valueOf("#fdfafa"));
        score.setFont(Font.font("Calibri Bold",32.0));

        Group group = new Group();
        group.getChildren().addAll(background,timer,score);

        return group;
    }

    public String getTimer() {
        return timer.getText();
    }

    public void setTimer(String time) {
        this.timer.setText(time);
    }

    public String getScore() {
        return score.getText();
    }

    public void setScore(String score) {
        this.score.setText(score);
    }
}
