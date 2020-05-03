package GUI;

import FruitNinja.GameController;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainScreen extends ParentScreen{

    private Scene scene;
    private Stage stage;
    private GameController gameController;
    private GameScreen gameScreen;

    private static MainScreen instance;
    private MainScreen(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public static MainScreen getInstance(Stage stage){
        if(instance==null)
            return instance = new MainScreen(stage);
        else
            return instance;
    }

    public void prepareScene() throws FileNotFoundException {

        BorderPane borderPane = new BorderPane();

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
        borderPane.setBackground(background);

        //
        FileInputStream fruitInput = new FileInputStream("fruit.png");
        Image fruitImage = new Image(fruitInput);
        ImageView fruitImageView = new ImageView(fruitImage);
        fruitImageView.setFitWidth(400);
        fruitImageView.setFitHeight(200);


        FileInputStream ninjaInput = new FileInputStream("ninja.png");
        Image ninjaImage = new Image(ninjaInput);
        ImageView ninjaImageView = new ImageView(ninjaImage);
        ninjaImageView.setFitWidth(400);
        ninjaImageView.setFitHeight(200);

        FileInputStream fruitSlicedInput = new FileInputStream("fruitSliced.png");
        Image fruitSlicedImage = new Image(fruitSlicedInput);


        FileInputStream ninjaSlicedInput = new FileInputStream("ninjaSliced.png");
        Image ninjaSlicedImage = new Image(ninjaSlicedInput);

        Button start = new Button("Start Game");
        start.setFont(new Font("Forte",55));
        start.setStyle("-fx-background-radius: 30;");

        start.setOnAction(e -> {
            try {
                gameController.showLevelSelectScene();
            } catch (FileNotFoundException | JAXBException ex) {
                ex.printStackTrace();
            }

        });

        RotateTransition fruitRotateTransition = new RotateTransition(Duration.seconds(1),fruitImageView);
        fruitRotateTransition.setToAngle(-5);
        fruitRotateTransition.setAutoReverse(true);
        fruitRotateTransition.setCycleCount(Animation.INDEFINITE);

        RotateTransition ninjaRotateTransition = new RotateTransition(Duration.seconds(1),ninjaImageView);
        ninjaRotateTransition.setToAngle(5);
        ninjaRotateTransition.setAutoReverse(true);
        ninjaRotateTransition.setCycleCount(Animation.INDEFINITE);


        Path path = new Path();
        path.getElements().add(new MoveTo(-200,0));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(200);
        arcTo.setY(-50);
        arcTo.setRadiusX(25);
        arcTo.setRadiusY(25);

        path.getElements().add(arcTo);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(fruitImageView);
        pathTransition.setDuration(Duration.seconds(3.5));
        pathTransition.setPath(path);
        pathTransition.play();
        pathTransition.setOnFinished(e->{
            fruitImageView.setImage(fruitSlicedImage);
            fruitRotateTransition.play();
        });


        TranslateTransition ninjaTransition = new TranslateTransition();
        ninjaTransition.setDuration(Duration.seconds(3.5));
        ninjaTransition.setNode(ninjaImageView);

        ninjaTransition.setFromX(400);
        ninjaTransition.setToX(0);

        ninjaTransition.play();

        ninjaTransition.setOnFinished(e->{
            gameController.sliceSound();

            ninjaImageView.setImage(ninjaSlicedImage);
            ninjaRotateTransition.play();
        });

        TranslateTransition startTransition = new TranslateTransition();
        startTransition.setDuration(Duration.seconds(3));
        startTransition.setNode(start);

        startTransition.setFromY(200);
        startTransition.setToY(-200);

        startTransition.play();

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(fruitImageView);
        hBox.getChildren().add(ninjaImageView);

        HBox hBox1 = new HBox(20);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(start);

        borderPane.setCenter(hBox);
        borderPane.setBottom(hBox1);

        scene = new Scene(borderPane, 1800, 900);
    }


    public Scene getScene() {
        return scene;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
