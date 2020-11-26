package main.java.GUI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


import java.io.File;

public class Taskbar {
    private Label timer;
    private Label score;
    private Label liver;
    private ImageView background;
    public MenuBar menu;
    public Menu file, help;
    public MenuItem saveGame, quit, about;
    public Group createTaskbar() {
        menu = new MenuBar();
        menu.setPrefHeight(26.0);
        menu.setPrefWidth(672.0);

        //Tạo các menu
         file = new Menu("File");
         help = new Menu("Help");

        // tạo các menu item
         saveGame = new MenuItem("Save Game");
         quit = new MenuItem("Quit");
         about = new MenuItem("About");

        // thêm các menuitem vào menu
        file.getItems().addAll(saveGame,quit);
        help.getItems().addAll(about);
        menu.getMenus().addAll(file, help);


        Image image = new Image(new File("res/taskbar.png").toURI().toString());
        background = new ImageView();
        background.setLayoutY(0);
        background.setLayoutY(26);
        background.setFitWidth(672.0);
        background.setFitHeight(80.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);
        background.setImage(image);

        timer = new Label();
        timer.setLayoutX(275.0);
        timer.setLayoutY(40.0);
        timer.setPrefHeight(35.0);
        timer.setPrefWidth(62.0);
        timer.setText("100");
        timer.setTextFill(Paint.valueOf("#fdfafa"));
        timer.setFont(Font.font("Calibri Bold",32.0));

        score = new Label();
        score.setLayoutY(40.0);
        score.setLayoutX(45.0);
        score.setPrefWidth(62.0);
        score.setPrefHeight(35.0);
        score.setText("180");
        score.setTextFill(Paint.valueOf("#fdfafa"));
        score.setFont(Font.font("Calibri Bold",35.0));

        liver = new Label();
        liver.setLayoutY(40.0);
        liver.setLayoutX(400.0);
        liver.setPrefWidth(28);
        liver.setPrefHeight(30);
        liver.setTextFill(Paint.valueOf("#fdfafa"));
        liver.setFont(Font.font("Calibri Bold",35.0));

        Group group = new Group();
        group.getChildren().addAll(menu,background,timer,score);

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
