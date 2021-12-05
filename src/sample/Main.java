package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.util.Optional;
import java.util.Scanner;

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
        Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);


        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(WIDTH / 2 - 120);
        backgroundOfMenu.setY(180);
        menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);


        Button accountButton = new Button("ENTER ACCOUNT");
        accountButton.setPrefSize(200, 30);
        accountButton.setTextFill(Color.PINK);
        accountButton.setBackground(background);
        menuRoot.getChildren().add(accountButton);
        accountButton.relocate(WIDTH / 2 - 100, 200);
        accountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(accountScene);
            }
        });

        Button accountMakeButton = new Button("LOG IN OR SIGN UP");
        accountMakeButton.setPrefSize(200, 30);
        accountMakeButton.setBackground(background);
        accountRoot.getChildren().add(accountMakeButton);
        accountMakeButton.setTextFill(Color.PINK);
        accountMakeButton.relocate(WIDTH / 2 - 100, 240);
        accountMakeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(getAccountScene);
                AccountScene.getSingleInstance().startGetAccount(getAccountScene, getAccountRoot, primaryStage, accountScene);
            }
        });

        Button leaderBoardButton = new Button("RANKING");
        leaderBoardButton.setBackground(background);
        leaderBoardButton.setPrefSize(200, 30);
        accountRoot.getChildren().add(leaderBoardButton);
        leaderBoardButton.setTextFill(Color.PINK);
        leaderBoardButton.relocate(WIDTH / 2 - 100, 280);
        leaderBoardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(rankScene);
                Account.showRanking(rankScene, rankRoot);
            }
        });


        Button playButton = new Button("PLAY");
        playButton.setBackground(background);
        playButton.setPrefSize(200, 30);
        accountRoot.getChildren().add(playButton);
        playButton.setTextFill(Color.PINK);
        playButton.relocate(WIDTH / 2 - 100, 200);
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (AccountScene.getSingleInstance().canPlay()) {
                    Group gameRoot = new Group();
                    setGameRoot(gameRoot);
                    Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
                    setGameScene(gameScene);
                    primaryStage.setScene(gameScene);
                    GameScene game = new GameScene();
                    game.game(gameScene, gameRoot, primaryStage, endGameScene, accountScene, endgameRoot);
                } else {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("You have not account");
                    a.show();
                }
            }
        });

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(200, 30);
        quitButton.setTextFill(Color.PINK);
        quitButton.setBackground(background);
        menuRoot.getChildren().add(quitButton);
        quitButton.relocate(WIDTH / 2 - 100, 240);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from game");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    primaryStage.close();
                }
            }
        });

        Button quitButtonFromRank = new Button("QUIT");
        quitButtonFromRank.setPrefSize(200, 30);
        quitButtonFromRank.setTextFill(Color.PINK);
        quitButtonFromRank.setBackground(background);
        rankRoot.getChildren().add(quitButtonFromRank);
        quitButtonFromRank.relocate(100, 800);
        quitButtonFromRank.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from Ranking");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    rankRoot.getChildren().clear();
                    rankRoot.getChildren().add(quitButtonFromRank);
                    primaryStage.setScene(accountScene);
                }
            }
        });


        primaryStage.setScene(menuScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
