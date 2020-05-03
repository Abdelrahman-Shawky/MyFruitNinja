package FruitNinja;

import Objects.GameObject;
import javafx.scene.layout.BorderPane;

import javax.xml.bind.JAXBException;

public interface GameActions {

    public GameObject createGameObject (int num,int round);
    public void updateObjectsLocations (BorderPane borderPane);
    public void updateObjectsLocationsArcade (BorderPane borderPane);
    public void saveGame () throws JAXBException;
    public void loadGame () throws JAXBException;
    public void resetGame();

}
