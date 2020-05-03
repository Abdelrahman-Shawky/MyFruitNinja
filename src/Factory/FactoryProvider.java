package Factory;

import javafx.stage.Stage;

public class FactoryProvider {

    private Stage stage;

    private static FactoryProvider instance;

    private FactoryProvider(Stage stage){
        this.stage = stage;
    }

    public static FactoryProvider getInstance(Stage stage){
        if(instance==null)
            return instance = new FactoryProvider(stage);
        else
            return instance;
    }


    public IFactory create(String factoryType){
        IFactory factory = null;

        if("gameObject".equalsIgnoreCase(factoryType))
            factory =  GameObjectFactory.getInstance();
        else if("screen".equalsIgnoreCase(factoryType))
            factory =  ScreenFactory.getInstance(stage);

        return factory;
    }
}
