package main.java.GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.File;

public class endGame {
    private ImageView backgound;
    private ImageView gameOver;
    public Button playAgain;
    public Button quit;

    public Group gameOverStatus() {
        backgound = new ImageView();
        backgound.setFitHeight(560.0);
        backgound.setFitWidth(640.0);
        backgound.setOpacity(0.36);
        backgound.setImage(new Image(new File("res/nen.png").toURI().toString()));

        gameOver = new ImageView();
        gameOver.setFitWidth(530.0);
        gameOver.setFitHeight(150.0);
        gameOver.setLayoutX(55.0);
        gameOver.setLayoutY(78.0);
        gameOver.setPickOnBounds(true);
        gameOver.setPreserveRatio(true);
        gameOver.setImage(new Image(new File("res/over.png").toURI().toString()));

        playAgain = new Button();
        playAgain.setLayoutX(183.0);
        playAgain.setLayoutY(234.0);
        playAgain.setMnemonicParsing(false);
        playAgain.setPrefHeight(92.0);
        playAgain.setPrefWidth(286.0);
        playAgain.setText("PLAY AGAIN");
        playAgain.setFont(Font.font("Arial Bold",36.0));

        quit = new Button();
        quit.setLayoutY(356.0);
        quit.setLayoutX(183.0);
        quit.setMnemonicParsing(false);
        quit.setPrefHeight(111.0);
        quit.setPrefWidth(286.0);
        quit.setText("QUIT");
        quit.setFont(Font.font("Arial Bold",39.0));
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
                System.exit(0);

            }
        });

        Group group = new Group();
        group.getChildren().addAll(backgound, gameOver, playAgain,quit);
        return group;
    }
}
