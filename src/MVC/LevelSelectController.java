package MVC;

import Factory.FactoryProvider;
import Factory.GameObjectFactory;
import Objects.GameObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Random;

public class LevelSelectController {

    private Stage stage;
    private Timeline timeline;

    public LevelSelectController(Stage stage) {
        this.stage = stage;
    }

    public void updateObjectsLocations(BorderPane borderPane){

        Random number = new Random();

        GameObject temp = createGameObject();
        ImageView imageView = temp.getBufferedImages();

        imageView.setX(100);
        imageView.setY(-150);

        TranslateTransition transition = new TranslateTransition();

        transition.setDuration(Duration.seconds(8));
        transition.setNode(imageView);

        borderPane.getChildren().add(imageView);

        transition.setFromX(number.nextInt(900) + 500);
        transition.setFromY(-150);
        transition.setToY(1200);

        transition.setOnFinished(e -> {
            borderPane.getChildren().remove(temp);
        });

        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.play();

    }

    public void startTransition(BorderPane borderPane){
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                updateObjectsLocations(borderPane);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public GameObject createGameObject(){

        FactoryProvider factoryProvider = FactoryProvider.getInstance(stage);
        GameObjectFactory gameObjectFactory = (GameObjectFactory) factoryProvider.create("gameObject");

        Random number = new Random();
        int random = number.nextInt(5) +1;

        switch (random) {
            case 1:
                return gameObjectFactory.create("Fruit1");
            case 2:
                return gameObjectFactory.create("Fruit2");
            case 3:
                return gameObjectFactory.create("Fruit3");
            case 4:
                return gameObjectFactory.create("specialFruit1");
            case 5:
                return gameObjectFactory.create("specialFruit2");
            default:
                return null;
        }
    }

    public void stop(){
        if(timeline!=null)
        timeline.stop();
    }

}
