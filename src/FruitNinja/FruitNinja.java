package FruitNinja;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class FruitNinja extends Application {


    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Fruit Ninja");

        GameController gameController = GameController.getInstance(primaryStage);

        primaryStage.setScene(gameController.getMainScreen().getScene());
        primaryStage.show();
    }
}
