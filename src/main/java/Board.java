package main.java;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.GUI.*;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.Agent;
import main.java.utils.GameSound;
import main.java.utils.GameStatus;
import main.java.utils.Input;


import javax.sound.sampled.Clip;
import java.io.*;

public class Board extends Application {

    public static final int WIDTH = 21;
    public static final int HEIGHT = 16;
    public static final int DEFAULT_SIZE = 16; // kich thuoc anh
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2; // kich thuoc khi in len man hinh

    private GraphicsContext gc;
    private Canvas canvas;
    public static GameState gameState;
    private Menu menu;
    private endGame gameOver;
    private winBoard winGame;
    private boolean multiplayer = false;
    private highScoreBoard yourHighScore;
    public static int currentLevel = 1;
    private int countTime = 180;
    private int numOfRefresh = 1;
    private boolean startGame = false;   // startGame = true thì mới bắt đầu tính thời gian
    private Clip clip;  // sound game menu
    private GameSound menuSound = new GameSound();
    private boolean devlScreamed = false;
    @Override
    public void start(Stage primaryStage) throws Exception {
        menu = new Menu();


        yourHighScore = new highScoreBoard();
        canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.setLayoutX(0);
        canvas.setLayoutY(106.0);
        Taskbar taskbar = new Taskbar();
        Group group = taskbar.createTaskbar();
        Group root = new Group();
        gameOver = new endGame();
        winGame = new winBoard();
        root.getChildren().addAll(canvas,group);

        Scene scene = new Scene(root);
        Scene scene1 = menu.createTaskbar();
        primaryStage.setScene(scene1);
        menuSound.playMenuBackground();
//        URL url = new File("src/main/resources/sounds/menu.wav").toURI().toURL();
//        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//        clip = AudioSystem.getClip();
//        clip.open(audioIn);
//        clip.start();


        menu.playWithFriend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                multiplayer = true;
                menuSound.menu.stop();
                primaryStage.setScene(scene);
                GameState.background = new GameSound();
                GameState.background.playBackgroundFx();

            }
        });

        menu.newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameState.removeEntity((Entity)gameState.getPlayerAgent(2));
                primaryStage.setScene(scene);
                startGame = true;
                menuSound.menu.stop();
                GameState.background = new GameSound();
                GameState.background.playBackgroundFx();
            }
        });

        menu.highScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Stage dialog = new Stage();
                    Group group1 = yourHighScore.createScoreBoard();
                    Scene newScene = new Scene(group1);
                    dialog.setScene(newScene);
                    dialog.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
                    System.exit(0);
                });
        gameState = new GameState(String.format("src/main/resources/levels/Level%d.txt", currentLevel), new Input(scene));


        Group group1 = gameOver.gameOverStatus();
        Scene scene2 = new Scene(group1);
        Group group2 = winGame.createWinBoard();
        Scene scene3 = new Scene(group2);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.4 / GameState.NUM_REFRESH_PER_TIME_UNIT), event -> {
            gameState.refresh();
            if(startGame) {
                numOfRefresh += 1; // đếm số lần refresh  150 lần refresh = 1s
            }
//            System.out.println(numOfRefresh);
            if(numOfRefresh % 150 == 0){
                countTime -= 1;
                taskbar.timer.setText(String.valueOf(countTime));
            }
            taskbar.score.setText(String.valueOf(gameState.getScore()));
            if(!gameState.isLose()){
                taskbar.liver.setText(String.valueOf(gameState.getPlayerAgent(1).getNumHearts()));
            }
            try {
                if(gameState.isLose()) {
                    this.countTime = 180;
                    this.numOfRefresh = 0;
                    gameOver.playAgain.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try {
                                if(multiplayer) {
                                    gameState = new GameState(String.format("src/main/resources/levels/Level%d.txt", currentLevel), new Input(scene));
                                    GameState.background = new GameSound();
                                    GameState.background.playBackgroundFx();
                                    gameState.setStatus(GameStatus.PLAYING);
                                }else {
                                    gameState = new GameState(String.format("src/main/resources/levels/Level%d.txt", currentLevel), new Input(scene));
                                    gameState.removeEntity((Entity) gameState.getPlayerAgent(2));
                                    GameState.background = new GameSound();
                                    GameState.background.playBackgroundFx();
                                    gameState.setStatus(GameStatus.PLAYING);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            primaryStage.setScene(scene);
                        }
                    });
                    primaryStage.setScene(scene2);
                }

                if(gameState.isWin() && currentLevel < 3){
                    currentLevel++;
                    GameState.background.clip.stop();
                    GameState.background.run = false;
                    GameSound win = new GameSound();
                    win.playWin();
                    GameState.background = new GameSound();
                    GameState.background.playBackgroundFx();
                    this.countTime = 188;
                    this.numOfRefresh = 0;
                    gameState = new GameState(String.format("src/main/resources/levels/Level%d.txt", currentLevel),  new Input(scene));
                    if(!multiplayer) {
                        gameState.removeEntity(gameState.getPlayerAgent(2));
                    }
                } else if(gameState.isWin() && currentLevel == 3) {
                    winGame.backToMenu.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(scene);

                        }
                    });
                    if(!devlScreamed) {
                        GameSound devl = new GameSound();
                        devl.playDeVl();
                        devlScreamed = true;
                    }

                    primaryStage.setScene(scene3);

                }
                taskbar.quit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String point = taskbar.getScore();
                        highScore.saveScore(point);

                        System.exit(0);
                    }
                });
                render();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // render();
        // scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        //     @Override
        //     public void handle(KeyEvent event) {
        //         gameState.addPlayerInput(event);
        //     }
        // });
        // scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        //     @Override
        //     public void handle(KeyEvent event) {
        //         gameState.addPlayerInput(event);
        //     }
        // });
    }

    public static void main(String[] args) {

        launch(args);

    }

    public void render() throws FileNotFoundException {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(Entity e : gameState.getListGrass()) {
            e.render(gc);
        }

        for(Entity e : gameState.getEntityList()) {
            if (!(e instanceof Agent) && e.isVisible()) {
                e.render(gc);
            }
        }

        for(Entity e : gameState.getEntityList()) {
            if (e instanceof Agent) {
                e.render(gc);
            }
        }
    }
}
