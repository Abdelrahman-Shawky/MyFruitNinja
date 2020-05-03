package GUI;

import MVC.LevelSelectController;
import FruitNinja.GameController;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LevelSelectScreen extends ParentScreen{

    private Stage stage;
    private Scene scene;
    private GameController gameController;

    private static LevelSelectScreen instance;
    private LevelSelectScreen(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public static LevelSelectScreen getInstance(Stage stage){
        if(instance==null)
            return instance = new LevelSelectScreen(stage);
        else
            return instance;
    }

    public void prepareScene() throws FileNotFoundException {

        LevelSelectController levelSelectController = new LevelSelectController(stage);

        VBox vBox = new VBox(20);

        FileInputStream input = new FileInputStream("wood.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        vBox.setBackground(background);

        Button easyButton = new Button("Easy");
        easyButton.setFont(new Font("Forte",55));
        easyButton.setStyle("-fx-background-radius: 30;");

        Button mediumButton = new Button("Medium");
        mediumButton.setFont(new Font("Forte",55));
        mediumButton.setStyle("-fx-background-radius: 30;");

        Button hardButton = new Button("Hard");
        hardButton.setFont(new Font("Forte",55));
        hardButton.setStyle("-fx-background-radius: 30;");

        Button arcadeButton = new Button("Arcade");
        arcadeButton.setFont(new Font("Forte",55));
        arcadeButton.setStyle("-fx-background-radius: 30;");

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(2));
        transition1.setNode(easyButton);
        transition1.setFromY(-200);
        transition1.setFromX(200);
        transition1.setToY(100);
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(2.5));
        transition2.setNode(mediumButton);
        transition2.setFromY(175);
        transition2.setFromX(1500);
        transition2.setToX(200);
        transition2.play();

        TranslateTransition transition3 = new TranslateTransition();
        transition3.setDuration(Duration.seconds(2.5));
        transition3.setNode(hardButton);
        transition3.setFromY(250);
        transition3.setFromX(1500);
        transition3.setToX(200);
        transition3.play();

        TranslateTransition transition4 = new TranslateTransition();
        transition4.setDuration(Duration.seconds(2.5));
        transition4.setNode(arcadeButton);
        transition4.setFromY(800);
        transition4.setFromX(200);
        transition4.setToY(325);
        transition4.play();


        BorderPane borderPane = new BorderPane();
        transition2.setOnFinished(e->{
            levelSelectController.startTransition(borderPane);

        });

        easyButton.setOnAction(e->{
            levelSelectController.stop();
            gameController.chooseLevel("easy");
            try {
                gameController.showGameScreenScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }


        });

        mediumButton.setOnAction(e->{
            levelSelectController.stop();
            gameController.chooseLevel("medium");
            try {
                gameController.showGameScreenScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });

        hardButton.setOnAction(e->{
            levelSelectController.stop();
            gameController.chooseLevel("hard");
            try {
                gameController.showGameScreenScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });

        arcadeButton.setOnAction(e->{
            levelSelectController.stop();
            gameController.chooseLevel("arcade");
            try {
                gameController.showGameScreenScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });

        vBox.getChildren().addAll(easyButton,mediumButton,hardButton,arcadeButton);
        borderPane.setCenter(vBox);




        scene = new Scene(borderPane,1800, 900);
    }

    public Scene getScene() {
        return scene;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
