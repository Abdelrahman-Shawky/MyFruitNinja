package Factory;

import GUI.*;
import javafx.stage.Stage;

public class ScreenFactory implements IFactory<ParentScreen>{

    private Stage stage;
    private static ScreenFactory instance;

    private ScreenFactory(Stage stage){
        this.stage = stage;
    }

    public static ScreenFactory getInstance(Stage stage){
        if(instance==null)
            return instance = new ScreenFactory(stage);
        else
            return instance;
    }


    @Override
    public ParentScreen create(String screenName){

        ParentScreen screen = null;
        if("mainscreen".equalsIgnoreCase(screenName))
            screen = MainScreen.getInstance(stage);
        else if("levelselect".equalsIgnoreCase(screenName))
            screen = LevelSelectScreen.getInstance(stage);
        else if("gamescreen".equalsIgnoreCase(screenName))
            screen = GameScreen.getInstance(stage);
        else if("gameoverscreen".equalsIgnoreCase(screenName))
            screen = GameOverScreen.getInstance(stage);
        else return null;

        return screen;

    }
}
