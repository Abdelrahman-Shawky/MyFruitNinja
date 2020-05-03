package Factory;

import GUI.ParentScreen;
import Objects.GameObject;
import javafx.stage.Stage;

public interface IFactory <T>{
    T create(String name);

}
