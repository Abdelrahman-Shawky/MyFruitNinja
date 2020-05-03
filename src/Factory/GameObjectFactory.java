package Factory;

import GUI.ParentScreen;
import Objects.*;
import javafx.stage.Stage;

public class GameObjectFactory implements IFactory<GameObject>{

    private static GameObjectFactory instance;

    private GameObjectFactory(){}

    public static GameObjectFactory getInstance(){
        if(instance==null)
            return instance = new GameObjectFactory();
        else
            return instance;
    }

    @Override
    public GameObject create(String objectName){
        GameObject gameObject = null;
        if("fruit1".equalsIgnoreCase(objectName))
            gameObject = new Fruit1();
        else if("fruit2".equalsIgnoreCase(objectName))
            gameObject = new Fruit2();
        else if("fruit3".equalsIgnoreCase(objectName))
            gameObject = new Fruit3();
        else if("specialFruit1".equalsIgnoreCase(objectName))
            gameObject = new SpecialFruit1();
        else if("specialFruit2".equalsIgnoreCase(objectName))
            gameObject = new SpecialFruit2();
        else if("regularBomb".equalsIgnoreCase(objectName))
            gameObject = new RegularBomb();
        else if("fatalBomb".equalsIgnoreCase(objectName))
            gameObject = new FatalBomb();
        else return null;

        return gameObject;

    }



}
