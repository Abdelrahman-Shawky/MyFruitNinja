package FruitNinja;

import Command.*;
import Factory.FactoryProvider;
import Factory.GameObjectFactory;
import Factory.ScreenFactory;
import GUI.*;
import MVC.GameModel;
import Memento.MementoCaretaker;
import Objects.*;
import SavingAdapters.GameMementoToXMLAdapter;
import SavingAdapters.PlayerSaveToPlayerAdapter;
import SavingAdapters.PlayerToPlayerSaveAdapter;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;

public class GameController implements GameActions{

    //Model
    private GameModel gameModel;

    //Views
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private LevelSelectScreen levelSelectScreen;
    private MainScreen mainScreen;
    private PauseScreen pauseScreen;

    //Controller
    private static GameController instance;
    private Stage stage;
    private FactoryProvider factoryProvider;
    private RemoteControl remoteControl;
    private MementoCaretaker mementoCaretaker;

    private Timeline timeline;
    private Timeline countingTimeline;

    private Save save;

    private List<TranslateTransition> transitions;
    private MediaPlayer backgroundMediaPlayer;
    private MediaPlayer effectsMediaPlayer;

    private GameController(Stage stage) throws JAXBException, FileNotFoundException {

        playBackgroundMusic();

        this.stage = stage;
        gameModel = GameModel.getInstance();
        loadGame();
        transitions = new ArrayList<>();

        this.factoryProvider = FactoryProvider.getInstance(stage);
        this.remoteControl = new RemoteControl();
        this.mementoCaretaker = new MementoCaretaker();

        SliceSound sliceSound = new SliceSound(effectsMediaPlayer);
        SliceSoundCommand sliceSoundCommand = new SliceSoundCommand(sliceSound);
        remoteControl.setCommand(0,sliceSoundCommand);

        ExplosionSound explosionSound = new ExplosionSound(effectsMediaPlayer);
        ExplosionSoundCommand explosionSoundCommand  = new ExplosionSoundCommand(explosionSound);
        remoteControl.setCommand(1,explosionSoundCommand);

        BuzzerSound buzzerSound = new BuzzerSound(effectsMediaPlayer);
        BuzzerSoundCommand buzzerSoundCommand = new BuzzerSoundCommand(buzzerSound);
        remoteControl.setCommand(2,buzzerSoundCommand);

        GameOverSound gameOverSound = new GameOverSound(effectsMediaPlayer);
        GameOverSoundCommand gameOverSoundCommand = new GameOverSoundCommand(gameOverSound);
        remoteControl.setCommand(3,gameOverSoundCommand);

        SliceSound2 sliceSound2 = new SliceSound2(effectsMediaPlayer);
        SliceSoundCommand2 sliceSoundCommand2 = new SliceSoundCommand2(sliceSound2);
        remoteControl.setCommand(4,sliceSoundCommand2);

        setScreens();
    }

    public static GameController getInstance(Stage stage) throws JAXBException, FileNotFoundException {
        if(instance==null)
            return instance = new GameController(stage);
        else
            return instance;
    }

    public void closeProgram(){
        ConfirmBox confirmbox = new ConfirmBox();
        boolean answer = confirmbox.message("Exit","Are You Sure You Want To Exit?");
        if (answer)
            stage.close();
        else if(stage.getScene() == gameScreen.getScene());
        {
            if (timeline != null && countingTimeline != null) {
                timeline.play();
                countingTimeline.play();

                for(Transition transition  : transitions)
                {
                    transition.play();
                }
            }
        }
    }

    private void setScreens() throws FileNotFoundException {

        ScreenFactory screenFactory = (ScreenFactory) factoryProvider.create("screen");

        mainScreen = (MainScreen) screenFactory.create("MainScreen");
        gameScreen = (GameScreen) screenFactory.create("gameScreen");
        levelSelectScreen = (LevelSelectScreen) screenFactory.create("LevelSelect");
        gameOverScreen = (GameOverScreen) screenFactory.create("GameOverScreen");
        pauseScreen = PauseScreen.getInstance(this);

        gameScreen.setGameController(this);
        mainScreen.setGameController(this);
        levelSelectScreen.setGameController(this);
        gameOverScreen.setGameController(this);

        gameModel.getModelPlayer().addObserver(gameScreen);

        mainScreen.prepareScene();

        stage.setOnCloseRequest(e ->{
            e.consume();

            if(stage.getScene() == gameScreen.getScene()) {

                if(transitions!=null)
                    for(Transition transition : transitions)
                    {
                        transition.pause();
                    }

            if(timeline!=null && countingTimeline!=null) {
                timeline.pause();
                countingTimeline.pause();
            }

                ConfirmBox confirmbox = new ConfirmBox();
                boolean answer = confirmbox.message("Exit", "Do You Want To Save?");
                if (answer) {
                    mementoCaretaker.createMemento(gameModel.save());
                    GameMementoToXMLAdapter gameMementoToXMLAdapter = new GameMementoToXMLAdapter(mementoCaretaker.getMemento().getCrossCount(),mementoCaretaker.getMemento().getLevel(),
                            mementoCaretaker.getMemento().getPlayer().getScore(),mementoCaretaker.getMemento().getRound(),mementoCaretaker.getMemento().getTimelineDuration(),
                            mementoCaretaker.getMemento().getTimerMinutes(),mementoCaretaker.getMemento().getTimerSeconds());
                    save.setGameMemento(gameMementoToXMLAdapter);

                    try {
                        saveGame();
                    } catch (JAXBException ex) {
                        ex.printStackTrace();
                    }
                    exit(1);

                } else {
                    save.setGameMemento(null);
                    try {
                        saveGame();
                    } catch (JAXBException ex) {
                        ex.printStackTrace();
                    }
                    closeProgram();
                }
            }
            else
            {
                closeProgram();
            }
        });
    }

    @Override
    public GameObject createGameObject(int num,int round) {

        FactoryProvider factoryProvider = FactoryProvider.getInstance(stage);
        GameObjectFactory gameObjectFactory = (GameObjectFactory) factoryProvider.create("gameObject");

        Random number = new Random();
        int random = number.nextInt(num) +1;

        if(gameModel.getModelLevel()==4)
        {
            switch (random) {
            case 1:
                return gameObjectFactory.create("Fruit1");
            case 2:
                return gameObjectFactory.create("Fruit2");
            case 3:
                return gameObjectFactory.create("Fruit3");
            case 4:
                gameModel.setModelSpecialFruit(true);
                return gameObjectFactory.create("specialFruit1");
            case 5:
                gameModel.setModelSpecialFruit(true);
                return gameObjectFactory.create("specialFruit2");
            default:
                return null;
        }
        }
        else if(round%2==0 && !gameModel.isModelSpecialFruit())
            switch (random+1) {
                case 1:
                    return gameObjectFactory.create("fatalBomb");
                case 2:
                    return gameObjectFactory.create("regularBomb");
                case 3:
                    return gameObjectFactory.create("Fruit1");
                case 4:
                    return gameObjectFactory.create("Fruit2");
                case 5:
                    return gameObjectFactory.create("Fruit3");
                case 6:
                    gameModel.setModelSpecialFruit(true);
                    return gameObjectFactory.create("specialFruit2");
                default:
                    return null;
            }
        else if(round%3==0 && !gameModel.isModelSpecialFruit())
            switch (random+1) {
                case 1:
                    return gameObjectFactory.create("fatalBomb");
                case 2:
                    return gameObjectFactory.create("regularBomb");
                case 3:
                    return gameObjectFactory.create("Fruit1");
                case 4:
                    return gameObjectFactory.create("Fruit2");
                case 5:
                    return gameObjectFactory.create("Fruit3");
                case 6:
                    gameModel.setModelSpecialFruit(true);
                    return gameObjectFactory.create("specialFruit1");
                default:
                    return null;
            }
            else {
            switch (random) {
                case 1:
                    return gameObjectFactory.create("fatalBomb");
                case 2:
                    return gameObjectFactory.create("regularBomb");
                case 3:
                    return gameObjectFactory.create("Fruit1");
                case 4:
                    return gameObjectFactory.create("Fruit2");
                case 5:
                    return gameObjectFactory.create("Fruit3");
                default:
                    return null;
            }
        }
    }

    @Override
    public void updateObjectsLocations(BorderPane borderPane) {

        transitions.clear();

        gameModel.setModelRoundPlus();
        gameModel.setModelSpecialFruit(false);

        int duration=4;
        int objectNumber=countObjects();

        if(gameModel.getModelLevel()==1)
            duration=4;
        else if(gameModel.getModelLevel()==2)
            objectNumber=countObjects()*2;
        else if(gameModel.getModelLevel()==3)
        {
            duration=3;
            objectNumber=countObjects()*2;
        }

        Random number = new Random();
        int random = number.nextInt(objectNumber)+1;
        System.out.println(random);

        for(int i=0; i<random; i++) {
            GameObject temp = createGameObject(countObjects(),gameModel.getModelRound());

            ImageView imageView = setImage(temp);

            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(duration*temp.getVelocity()));
            transition.setNode(imageView);

            transitions.add(transition);

            borderPane.getChildren().add(imageView);

            transition.setFromX(number.nextInt(900) + 300);
            transition.setToY(-number.nextInt(400) - 800);

            transition.setOnFinished(e -> {

                borderPane.getChildren().remove(temp);
                if(!temp.isBomb() && !temp.isSliced() && gameModel.getModelCrossCount()!=3)
                {
                    remoteControl.play(2);
                    gameModel.setModelCrossCountPlus();
                    if(gameModel.getModelCrossCount()==3)
                    {
                        try {
                            saveGame();
                        } catch (JAXBException ex) {
                            ex.printStackTrace();
                        }
                        timeline.stop();
                        countingTimeline.stop();
                        showGameOver();
                    }
                }
            });

            transition.setAutoReverse(true);
            transition.setCycleCount(2);
            transition.play();
        }
    }

    @Override
    public void updateObjectsLocationsArcade(BorderPane borderPane){

        gameModel.setModelRoundPlus();
        gameModel.setModelSpecialFruit(false);

        Random number = new Random();

        GameObject temp = createGameObject(countObjects(), gameModel.getModelRound());

        ImageView imageView = setImage(temp);

        TranslateTransition transition = new TranslateTransition();

        if(gameModel.getModelTimerSeconds()<20)
            transition.setDuration(Duration.seconds(4*temp.getVelocity()));
        else if(gameModel.getModelTimerSeconds()<40)
            transition.setDuration(Duration.seconds(3*temp.getVelocity()));
        else
            transition.setDuration(Duration.seconds(2*temp.getVelocity()));

        transition.setNode(imageView);

        borderPane.getChildren().add(imageView);

        transition.setFromX(number.nextInt(900) + 300);
        transition.setToY(-number.nextInt(400) - 800);

        transitions.add(transition);

        transition.setOnFinished(e -> {
            transitions.remove(transition);
            borderPane.getChildren().remove(temp);
        });

        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.play();

        if(gameModel.getModelTimerMinutes()==1) {
            timeline.stop();
            countingTimeline.stop();
//            resetGame();
            try {
                saveGame();
                showArcadeGameOverScreenScene();
            } catch (JAXBException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveGame() throws JAXBException {

        PlayerToPlayerSaveAdapter playerToPlayerSaveAdapter = new PlayerToPlayerSaveAdapter(gameModel.getModelPlayer().getEasyHighScore(),gameModel.getModelPlayer().getMediumHighScore(),gameModel.getModelPlayer().getHardHighScore(),gameModel.getModelPlayer().getArcadeHighScore());
        save.setPlayerSave(playerToPlayerSaveAdapter);

        JAXBContext jaxbContext = JAXBContext.newInstance(Save.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(save, new File("save.xml"));
    }

    @Override
    public void loadGame() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Save.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        save = (Save) unmarshaller.unmarshal(new File("save.xml"));

        PlayerSaveToPlayerAdapter playerSaveToPlayerAdapter;

        if(save.getPlayerSave() == null)
            playerSaveToPlayerAdapter = new PlayerSaveToPlayerAdapter(0,0,0,0);
        else
            playerSaveToPlayerAdapter = new PlayerSaveToPlayerAdapter(save.getPlayerSave().getEasyHighScore(),save.getPlayerSave().getMediumHighScore(),save.getPlayerSave().getHardHighScore(),save.getPlayerSave().getArcadeHighScore());

        gameModel.setModelPlayer(playerSaveToPlayerAdapter.getPlayer());
    }

    @Override
    public void resetGame() {

        gameModel.setModelRound(0);
        gameModel.setModelCrossCount(0);
        gameModel.setModelTimerMinutes(0);
        gameModel.setModelTimerSeconds(0);
        gameModel.getModelPlayer().setScore(0);
        if(transitions!=null)
        for(Transition transition : transitions)
        {
            transition.stop();
        }
    }

    private ImageView setImage(GameObject gameObject){

        ImageView imageView = gameObject.getBufferedImages();

        imageView.setX(100);
        imageView.setY(1000);

        imageView.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if(gameObject.isBomb() && gameModel.getModelCrossCount()!=3)
                {
                    if(gameObject.isFatal())
                    {
                        timeline.stop();
                        remoteControl.play(1);

                        gameModel.setModelCrossCountPlus();
                        gameModel.setModelCrossCountPlus();
                        gameModel.setModelCrossCountPlus();
                        if(gameModel.getModelCrossCount()==3)
                        {
                            try {
                                saveGame();
                            } catch (JAXBException ex) {
                                ex.printStackTrace();
                            }
                            timeline.stop();
                            countingTimeline.stop();
                            showGameOver();
                        }
                    }
                    if(!gameObject.isSliced()) {
                        remoteControl.play(1);

                        gameModel.setModelCrossCountPlus();
                        if(gameModel.getModelCrossCount()==3)
                        {
                            try {
                                saveGame();
                            } catch (JAXBException ex) {
                                ex.printStackTrace();
                            }
                            timeline.stop();
                            countingTimeline.stop();
                            showGameOver();
                        }
                        gameObject.slice();
                    }
                }
                else if(!gameObject.isSliced() && gameModel.getModelCrossCount()!=3) {

                    if(gameObject.isSpecialFruit1()) {
                        gameModel.setModelCrossCountMinus();
                    }
                    else if(gameObject.isSpecialFruit2()) {
                        for(int i=0;i<4;i++)
                            gameModel.getModelPlayer().setScorePlus();
                    }

                    Random random = new Random();
                    switch (random.nextInt(2)+1)
                    {
                        case 1:
                            remoteControl.play(0);
                            break;
                        case 2:
                            remoteControl.play(4);
                            break;
                    }
                    gameModel.getModelPlayer().setScorePlus();

                    gameModel.setModelHighScore();
                    gameObject.slice();
                    imageView.setImage(gameObject.getBufferedImages().getImage());
                }
            }
        });
        return imageView;
    }

    public void chooseLevel(String difficulty){
        switch (difficulty.toLowerCase()){
            case "easy":
                gameModel.setModelLevel(1);
                gameModel.setModelTimelineDuration(8.5);
                break;
            case"medium":
                gameModel.setModelTimelineDuration(8.5);
                gameModel.setModelLevel(2);
                break;
            case"hard":
                gameModel.setModelTimelineDuration(6.5);
                gameModel.setModelLevel(3);
                break;
            case"arcade":
                gameModel.setModelTimelineDuration(0.4);
                gameModel.setModelLevel(4);
                break;
            default:
                break;
        }
    }

    private void showTime(){
        countingTimeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                gameModel.setModelTimerSecondsPlus();
                if(gameModel.getModelTimerSeconds()%60==0)
                {
                    gameModel.setModelTimerSeconds(0);
                    gameModel.setModelTimerMinutesPlus();
                }
            }
        })
        );
        countingTimeline.setCycleCount(Animation.INDEFINITE);
        countingTimeline.play();
    }

    public void startGame(BorderPane borderPane) throws JAXBException {

        remoteControl.play(0);

        backgroundMediaPlayer.stop();
        String name = "backgroundMusic2.mp3";
        Media media = new Media(Paths.get(name).toUri().toString());
        backgroundMediaPlayer = new MediaPlayer(media);
        backgroundMediaPlayer.setAutoPlay(true);
        backgroundMediaPlayer.setVolume(0.5);
        backgroundMediaPlayer.play();
        backgroundMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                backgroundMediaPlayer.seek(Duration.ZERO);
                backgroundMediaPlayer.play();
            }
        });

        showTime();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(gameModel.getModelTimelineDuration()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(gameModel.getModelLevel()==4) {
                            updateObjectsLocationsArcade(borderPane);
                        }
                        else {
                            updateObjectsLocations(borderPane);
                        }
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void showGameOver(){

        remoteControl.play(3);

        ParallelTransition parallelTransition = new ParallelTransition(gameScreen.getGameOverScaleTransition(),gameScreen.getGameOverRotateTransition());
        parallelTransition.play();

        parallelTransition.setOnFinished(e->{
            try {
                showNormalGameOverScreenScene();
            } catch (JAXBException | FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private int countObjects(){
        FatalBomb fatalBomb = new FatalBomb();
        RegularBomb regularBomb = new RegularBomb();
        Fruit1 fruit1 = new Fruit1();
        Fruit2 fruit2 = new Fruit2();
        Fruit3 fruit3 = new Fruit3();

        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(fatalBomb);
        gameObjects.add(regularBomb);
        gameObjects.add(fruit1);
        gameObjects.add(fruit2);
        gameObjects.add(fruit3);

        return gameObjects.size();
    }

    private void showNormalGameOverScreenScene() throws JAXBException, FileNotFoundException {
        save.setGameMemento(null);
        saveGame();

        gameOverScreen.prepareScene();
        gameOverScreen.getGameOverLabel().setText("Game Over");

        if(gameModel.getModelPlayer().getScore()==gameModel.getHighScore())
            gameOverScreen.getYourScoreLabel().setText("New HighScore: " + gameModel.getModelPlayer().getScore());
        else
        {
            gameOverScreen.getYourScoreLabel().setText("Score: " + gameModel.getModelPlayer().getScore());
            gameOverScreen.getHighScoreLabel().setText("HighScore: "+ gameModel.getHighScore());
        }
        backgroundMediaPlayer.stop();
        stage.setScene(gameOverScreen.getScene());
    }

    private void showArcadeGameOverScreenScene() throws JAXBException, FileNotFoundException {
        save.setGameMemento(null);
        saveGame();

        gameOverScreen.prepareScene();
        gameOverScreen.getGameOverLabel().setText("Well Done");

        if(gameModel.getHighScore()==gameModel.getModelPlayer().getScore())
            gameOverScreen.getYourScoreLabel().setText("New HighScore: "+ gameModel.getModelPlayer().getScore());
        else {
            gameOverScreen.getYourScoreLabel().setText("Score: "+ gameModel.getModelPlayer().getScore());
            gameOverScreen.getHighScoreLabel().setText("HighScore: " + gameModel.getHighScore());
        }
        stage.setScene(gameOverScreen.getScene());
    }

    public void showLevelSelectScene() throws FileNotFoundException, JAXBException {

        remoteControl.play(0);

        if(save.getGameMemento()!=null)
        {
            ConfirmBox confirmbox = new ConfirmBox();
            boolean answer = confirmbox.message("Exit","Would you Like To Continue Last Game?");
            if (answer)
            {

                gameModel.setModelTimelineDuration(save.getGameMemento().getTimelineDuration());
                gameModel.setModelLevel(save.getGameMemento().getLevel());
                gameModel.setModelCrossCount(save.getGameMemento().getCrossCount());
                gameModel.setModelTimerMinutes(save.getGameMemento().getTimerMinutes());
                gameModel.setModelTimerSeconds(save.getGameMemento().getTimerSeconds());
                gameModel.setModelRound(save.getGameMemento().getRound());
                gameModel.getModelPlayer().setScore(save.getGameMemento().getScore());

                gameScreen.prepareScene();
                gameModel.checkCross();
                stage.setScene(gameScreen.getScene());
            }
            else {
                save.setGameMemento(null);
                saveGame();
                levelSelectScreen.prepareScene();
                stage.setScene(levelSelectScreen.getScene());
            }
        }
        else
        {
            levelSelectScreen.prepareScene();
            stage.setScene(levelSelectScreen.getScene());
        }
    }

    public void showGameScreenScene() throws FileNotFoundException, JAXBException {
        remoteControl.play(0);

            gameScreen.prepareScene();
            stage.setScene(gameScreen.getScene());
    }

    public void showMainScreenScene() throws FileNotFoundException {
        playBackgroundMusic();
        mainScreen.prepareScene();
        stage.setScene(mainScreen.getScene());
    }

    public void pauseMenu() throws FileNotFoundException {
        timeline.pause();
        countingTimeline.pause();

        for(Transition transition : transitions)
        {
            transition.pause();
        }
        pauseScreen.pause();
    }

    public void playFromPause(){

        timeline.play();
        countingTimeline.play();

        for(Transition transition : transitions)
        {
            transition.play();
        }
    }

    public void retryFromPause() throws FileNotFoundException, JAXBException {
        resetGame();
        showGameScreenScene();
    }

    public void exitFromPause() throws FileNotFoundException {
        resetGame();
        backgroundMediaPlayer.stop();
        showMainScreenScene();
    }


    public void playBackgroundMusic(){

        String name = "backgroundMusic.mp3";
        Media media = new Media(Paths.get(name).toUri().toString());
        backgroundMediaPlayer = new MediaPlayer(media);
        backgroundMediaPlayer.setAutoPlay(true);
        backgroundMediaPlayer.play();

        backgroundMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                backgroundMediaPlayer.seek(Duration.ZERO);
                backgroundMediaPlayer.play();
            }
        });
    }

    public void sliceSound(){
        remoteControl.play(0);
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }
}
