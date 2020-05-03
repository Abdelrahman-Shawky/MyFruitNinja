package GUI;

import FruitNinja.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class PauseScreen {

    private GameController gameController;
    private static PauseScreen instance;

    private PauseScreen(GameController gameController) {
        this.gameController = gameController;
    }

    public static PauseScreen getInstance(GameController gameController){
        if(instance==null)
            return instance = new PauseScreen(gameController);
        else
            return instance;
    }

    public void pause() throws FileNotFoundException {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label pauseLabel = new Label("PAUSED");
        pauseLabel.setFont(new Font("Forte",70));
        pauseLabel.setTextFill(Color.RED);

        Button playButton = new Button("Play");
        playButton.setFont(new Font("Forte",40));
        playButton.setStyle("-fx-background-radius: 30 ");

        Button retryButton = new Button("Retry");
        retryButton.setFont(new Font("Forte",40));
        retryButton.setStyle("-fx-background-radius: 30 ");

        Button exitButton = new Button("Exit");
        exitButton.setFont(new Font("Forte",40));
        exitButton.setStyle("-fx-background-radius: 30 ");


        playButton.setOnAction(e -> {
            gameController.playFromPause();
            stage.close();
        });

        retryButton.setOnAction(e -> {
            try {
                gameController.retryFromPause();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            stage.close();
        });

        exitButton.setOnAction(e->{
            try {
                gameController.exitFromPause();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            stage.close();
        });

        stage.setOnCloseRequest(e->{
            e.consume();
            gameController.playFromPause();
            stage.close();
        });

        HBox box = new HBox(15);
        box.getChildren().addAll(playButton,retryButton,exitButton);
        box.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(pauseLabel,box);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,600,400);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
