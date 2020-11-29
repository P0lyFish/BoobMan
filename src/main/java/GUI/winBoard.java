package main.java.GUI;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.File;


public class winBoard {
    private ImageView background;
    private ImageView title;
    public Button backToMenu;
    private Button quit;

    public Group createWinBoard() {
        background = new ImageView();
        background.setFitHeight(560.0);
        background.setFitWidth(640.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);
        background.setLayoutY(0);
        background.setLayoutX(0);
        background.setImage(new Image(new File("res/nen.png").toURI().toString()));

        title = new ImageView();
        title.setFitHeight(150.0);
        title.setFitWidth(560.0);
        title.setLayoutX(48.0);
        title.setLayoutY(35.0);
        title.setPickOnBounds(true);
        title.setPreserveRatio(true);
        title.setImage(new Image(new File("res/gamewin.png").toURI().toString()));

        backToMenu = new Button();
        backToMenu.setLayoutX(141.0);
        backToMenu.setLayoutY(243.0);
        backToMenu.setMnemonicParsing(false);
        backToMenu.setPrefHeight(95.0);
        backToMenu.setPrefWidth(376.0);
        backToMenu.setText("BACK TO MENU");
        backToMenu.setFont(Font.font("Arial Bold",40.0));

        quit = new Button();
        quit.setLayoutX(141.0);
        quit.setLayoutY(368.0);
        quit.setMnemonicParsing(false);
        quit.setPrefHeight(105.0);
        quit.setPrefWidth(376.0);
        quit.setText("QUIT GAME");
        quit.setFont(Font.font("Arial Bold",49.0));
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        Group group = new Group();
        group.getChildren().addAll(background,title, backToMenu, quit);
        return group;
    }
}
