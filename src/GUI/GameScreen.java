package GUI;

import FruitNinja.GameController;
import Observer.Observer;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBException;
import java.io.*;

public class GameScreen extends ParentScreen implements Observer {


    private Scene scene;
    private Stage stage;
    private ImageView[] crossImageView;
    private Label scoreValue;
    private Label highScoreValue;
    private GameController gameController;
    private Label timerValue;
    private Label gameOver;
    private ScaleTransition gameOverScaleTransition;
    private RotateTransition gameOverRotateTransition;


    private static GameScreen instance;
    private GameScreen(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public static GameScreen getInstance(Stage stage){
        if(instance==null)
            return instance = new GameScreen(stage);
        else
            return instance;
    }

    public void prepareScene() throws FileNotFoundException, JAXBException {

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(15);
        HBox hBox = new HBox();

        FileInputStream backgroundImageInput = new FileInputStream("wood.jpg");
        Image backgroundImageImage = new Image(backgroundImageInput);
        ImageView BackgroundImageView = new ImageView(backgroundImageImage);
        BackgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        BackgroundImageView.fitHeightProperty().bind(stage.heightProperty());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundImageImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        vBox.setBackground(background);

        Label scoreLabel = new Label("Score: ");
        scoreLabel.setFont(new Font("Forte",80));
        scoreLabel.setTextFill(Color.RED);

        scoreValue = new Label();
        scoreValue.setFont(new Font("Forte",80));
        scoreValue.setTextFill(Color.RED);

        Label highestScoreLabel = new Label("Highest Score: ");
        highestScoreLabel.setFont(new Font("Forte",55));
        highestScoreLabel.setTextFill(Color.RED);

        highScoreValue = new Label();
        highScoreValue.setFont(new Font("Forte",55));
        highScoreValue.setTextFill(Color.RED);

        Label timerLabel = new Label("time: ");
        timerLabel.setFont(new Font("Forte",55));
        timerLabel.setTextFill(Color.RED);

        timerValue = new Label();
        timerValue.setFont(new Font("Forte",55));
        timerValue.setTextFill(Color.RED);

        gameOver = new Label("Game Over");
        gameOver.setFont(new Font("Forte",1));
        gameOver.setTextFill(Color.RED);

        gameOverScaleTransition = new ScaleTransition(Duration.seconds(4),gameOver);
        gameOverScaleTransition.setFromX(0);
        gameOverScaleTransition.setFromY(0);
        gameOverScaleTransition.setToX(200);
        gameOverScaleTransition.setToY(200);

        gameOverRotateTransition = new RotateTransition(Duration.seconds(5),gameOver);
        gameOverRotateTransition.setToAngle(-10);

        borderPane.setCenter(gameOver);

        Button pause = new Button("||");
        pause.setFont(new Font("Forte",40));
        pause.setStyle("-fx-background-radius: 70;");
        pause.setVisible(false);

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(timerLabel,timerValue);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(highestScoreLabel,highScoreValue,region,pause);

        FileInputStream crossInput = new FileInputStream("greenX.png");
        Image crossImage = new Image(crossInput);

        crossImageView = new ImageView[3];
        crossImageView[0] = new ImageView(crossImage);
        crossImageView[1] = new ImageView(crossImage);
        crossImageView[2] = new ImageView(crossImage);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        hBox.getChildren().addAll(scoreLabel,scoreValue,region2,crossImageView[0],crossImageView[1],crossImageView[2]);
        hBox.setAlignment(Pos.TOP_RIGHT);

        vBox.getChildren().addAll(hBox,hBox1,hBox2,borderPane);

        Button start = new Button("Start");
        start.setFont(new Font("Forte",80));
        start.setStyle("-fx-background-radius: 30;");

        HBox hBox3 = new HBox();
        hBox3.getChildren().add(start);
        hBox3.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBox3);

        start.setOnAction(e -> {
            start.setVisible(false);
            pause.setVisible(true);

            try {
                gameController.startGame(borderPane);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });

        pause.setOnAction(e ->{
            try {
                gameController.pauseMenu();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        vBox.setOnDragDetected(e-> {
            vBox.startFullDrag();

        });

        scene = new Scene(vBox, 1800, 900);
    }

    @Override
    public void update(int score) {
        scoreValue.setText(String.valueOf(score));
    }

    @Override
    public void update(int timerMinutes,int timerSeconds,int highScore) {
        timerValue.setText(timerMinutes + ":" + timerSeconds);
        highScoreValue.setText(String.valueOf(highScore));
    }

    @Override
    public void update(int timerMinutes, int timerSeconds, int highScore, int count) {
        timerValue.setText(timerMinutes + ":" + timerSeconds);
        highScoreValue.setText(String.valueOf(highScore));

        FileInputStream crossInput = null;
        try {
            crossInput = new FileInputStream("redX.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image crossImage = new Image(crossInput);

        crossImageView[count-1].setImage(crossImage);
        if(count==2)
            crossImageView[count-2].setImage(crossImage);
    }

    @Override
    public void update(int timerMinutes, int timerSeconds, int highScore, int count,boolean cross) {
        timerValue.setText(timerMinutes + ":" + timerSeconds);
        highScoreValue.setText(String.valueOf(highScore));

        if(cross) {
            FileInputStream crossInput = null;
            try {
                crossInput = new FileInputStream("redX.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image crossImage = new Image(crossInput);

            crossImageView[count].setImage(crossImage);
        }
        else
        {
            FileInputStream crossInput = null;
            try {
                crossInput = new FileInputStream("greenX.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image crossImage = new Image(crossInput);

            crossImageView[count-1].setImage(crossImage);

        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public ScaleTransition getGameOverScaleTransition() {
        return gameOverScaleTransition;
    }

    public RotateTransition getGameOverRotateTransition() {
        return gameOverRotateTransition;
    }
}
