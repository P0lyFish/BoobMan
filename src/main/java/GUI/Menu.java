package main.java.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

//public class Menu extends Application {
public class Menu{
    private ImageView background;
    public Button newGame;
    public Button playWithFriend;
    public Button highScore;
    private Button exitGame;

//    @Override
//    public void start(Stage stage) throws Exception {
    public Scene createTaskbar() {
        String current = System.getProperty("user.dir");
        System.out.println(current);




        Image image = new Image(new File("res/nen.png").toURI().toString());
        background = new ImageView();
        background.setFitHeight(560.0);
        background.setFitWidth(640.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);
        background.setLayoutX(0);
        background.setLayoutY(26);
        background.setImage(image);

        newGame = new Button();
        newGame.setLayoutX(202.0);
        newGame.setLayoutY(90.0);
        newGame.setMnemonicParsing(false);
        newGame.setPrefHeight(76.0);
        newGame.setPrefWidth(236.0);
        newGame.setText("NEW GAME");
        newGame.setFont(Font.font("Arial Bold",32.0));
        newGame.setOpacity(0.7);

        playWithFriend = new Button();
        playWithFriend.setLayoutX(202.0);
        playWithFriend.setLayoutY(190.0);
        playWithFriend.setMnemonicParsing(false);
        playWithFriend.setPrefHeight(76.0);
        playWithFriend.setPrefWidth(236.0);
        playWithFriend.setText("PLAY WITH FRIEND");
        playWithFriend.setFont(Font.font("Arial Bold",22.0));
        playWithFriend.setOpacity(0.7);

        highScore = new Button();
        highScore.setLayoutX(202.0);
        highScore.setLayoutY(290.0);
        highScore.setMnemonicParsing(false);
        highScore.setPrefHeight(76.0);
        highScore.setPrefWidth(236.0);
        highScore.setText("HIGH SCORE");
        highScore.setFont(Font.font("Arial Bold",30.0));
        highScore.setOpacity(0.7);

        exitGame = new Button();
        exitGame.setLayoutX(202.0);
        exitGame.setLayoutY(387.0);
        exitGame.setMnemonicParsing(false);
        exitGame.setPrefHeight(76.0);
        exitGame.setPrefWidth(236.0);
        exitGame.setText("EXIT");
        exitGame.setFont(Font.font("Arial Bold",41.0));
        exitGame.setOpacity(0.7);



        //Handler Action
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        playWithFriend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        highScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        Group group = new Group();
        group.getChildren().addAll(background,newGame,playWithFriend,highScore,exitGame);


        Scene scene = new Scene(group,640,560);
//        stage.setScene(scene);
//        stage.show();
        return scene;
    }
//    public static void main(String[] args) {launch(args);}
}
