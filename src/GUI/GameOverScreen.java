package GUI;

import FruitNinja.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameOverScreen extends ParentScreen{

    private Scene scene;
    private Stage stage;
    private GameController gameController;

    private Label highScoreLabel;
    private Label gameOverLabel;
    private Label yourScoreLabel;

    private static GameOverScreen instance;
    private GameOverScreen(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public static GameOverScreen getInstance(Stage stage){
        if(instance==null)
            return instance = new GameOverScreen(stage);
        else
            return instance;
    }

    public void prepareScene() throws FileNotFoundException {

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

        gameOverLabel = new Label();
        gameOverLabel.setFont(new Font("Forte", 80));
        gameOverLabel.setTextFill(Color.RED);

        yourScoreLabel = new Label();
        yourScoreLabel.setFont(new Font("Forte", 80));
        yourScoreLabel.setTextFill(Color.RED);

        highScoreLabel = new Label();
        highScoreLabel.setFont(new Font("Forte", 80));
        highScoreLabel.setTextFill(Color.RED);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(gameOverLabel,yourScoreLabel,highScoreLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(background);

        vBox.setOnMouseClicked(e -> {
            try {
                gameController.resetGame();
                gameController.showMainScreenScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        scene = new Scene(vBox, 1800, 900);
    }

    public Scene getScene() {
        return scene;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public Label getHighScoreLabel() {
        return highScoreLabel;
    }

    public Label getGameOverLabel() {
        return gameOverLabel;
    }

    public Label getYourScoreLabel() {
        return yourScoreLabel;
    }
}
