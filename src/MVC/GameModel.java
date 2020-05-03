package MVC;

import FruitNinja.Player;
import Memento.GameMemento;

public class GameModel {

    private int crossCount;
    private int round;
    private int timerMinutes;
    private int timerSeconds;
    private int level;
    private boolean specialFruit;
    private Player player;
    private double timelineDuration;
    private int highScore;

    private static GameModel instance;

    private GameModel() {
        this.crossCount = 0;
        this.round = 0;
        this.timerMinutes = 0;
        this.timerSeconds = 0;
        this.level = 0;
        this.specialFruit = false;

    }

    public static GameModel getInstance(){
        if(instance==null)
            return instance = new GameModel();
        else
            return instance;
    }

    public GameMemento save(){
        return new GameMemento(crossCount,round,timerMinutes,timerSeconds,level,player,timelineDuration);
    }

    public void restore(GameMemento gameMemento){
        this.crossCount = gameMemento.getCrossCount();
        this.round = gameMemento.getRound();
        this.timerMinutes = gameMemento.getTimerMinutes();
        this.timerSeconds = gameMemento.getTimerSeconds();
        this.level = gameMemento.getLevel();
        this.specialFruit = false;
    }
    public void setModelTimerMinutesPlus() {
        this.timerMinutes++;
        player.notifyObservers(timerMinutes,timerSeconds,highScore);
    }

    public void setModelTimerSecondsPlus() {
        this.timerSeconds++;
        player.notifyObservers(timerMinutes,timerSeconds,highScore);
    }

    public int getHighScore() {
        return highScore;
    }

    public void setModelPlayer(Player player) {
        this.player = player;
        player.notifyObservers(timerMinutes,timerSeconds,highScore);
    }

    public void setModelLevel(int level) {
        this.level = level;
        setHighScore();
    }

    public void setHighScore() {
        if(level==1)
            this.highScore = player.getEasyHighScore();
        else if(level==2)
            this.highScore = player.getMediumHighScore();
        else if(level==3)
            this.highScore = player.getHardHighScore();
        else if(level==4)
            this.highScore = player.getArcadeHighScore();
    }
    public void setModelHighScore() {
        if(level==1) {
            if (player.getScore() > highScore) {
                player.setEasyHighScore(player.getScore());
                this.highScore = player.getScore();
                player.notifyObservers(timerMinutes,timerSeconds,player.getScore());
            }
        }
        else if(level==2) {
            if (player.getScore() > highScore) {
                player.setMediumHighScore(player.getScore());
                this.highScore = player.getScore();
                player.notifyObservers(timerMinutes,timerSeconds,player.getScore());
            }
        }
        else if(level==3) {
            if (player.getScore() > highScore) {
                player.setHardHighScore(player.getScore());
                this.highScore = player.getScore();
                player.notifyObservers(timerMinutes,timerSeconds,player.getScore());
            }
        }
        else if(level==4) {
            if (player.getScore() > highScore) {
                player.setArcadeHighScore(player.getScore());
                this.highScore = player.getScore();
                player.notifyObservers(timerMinutes,timerSeconds,player.getScore());
            }
        }
    }

    public void setModelCrossCountPlus() {

        if (crossCount < 3) {

            player.notifyObservers(timerMinutes,timerSeconds,player.getScore(),crossCount,true);
            this.crossCount++;

            if (crossCount == 3) {

                if(level==1) {
                    if (player.getScore() > player.getEasyHighScore()) {
                        System.out.println("save");
                        player.setEasyHighScore(player.getScore());
                        this.highScore = player.getScore();
                    }
                }
                else if(level==2) {
                    if (player.getScore() > player.getMediumHighScore()) {
                        System.out.println("save");
                        player.setMediumHighScore(player.getScore());
                        this.highScore = player.getScore();
                    }
                }
                else if(level==3) {
                    if (player.getScore() > player.getHardHighScore()) {
                        System.out.println("save");
                        player.setHardHighScore(player.getScore());
                        this.highScore = player.getScore();
                    }
                }
                else if(level==4) {
                    if (player.getScore() > player.getArcadeHighScore()) {
                        System.out.println("save");
                        player.setArcadeHighScore(player.getScore());
                        this.highScore = player.getScore();
                    }
                }
            }
        }
    }

    public void setModelCrossCountMinus() {

        if(crossCount>0)
        {
            player.notifyObservers(timerMinutes,timerSeconds,player.getScore(),crossCount,false);
            this.crossCount--;
        }
    }

    public void checkCross(){
        if(crossCount!=0)
            player.notifyObservers(timerMinutes,timerSeconds,player.getScore(),crossCount);
    }

    public int getModelCrossCount() {
        return crossCount;
    }

    public void setModelCrossCount(int crossCount) {
        this.crossCount = crossCount;
    }

    public int getModelRound() {
        return round;
    }

    public void setModelRound(int round) {
        this.round = round;
    }

    public void setModelRoundPlus() {
        this.round++;
    }

    public int getModelTimerMinutes() {
        return timerMinutes;
    }

    public void setModelTimerMinutes(int timerMinutes) {
        this.timerMinutes = timerMinutes;
    }

    public int getModelTimerSeconds() {
        return timerSeconds;
    }

    public void setModelTimerSeconds(int timerSeconds) {
        this.timerSeconds = timerSeconds;
    }

    public int getModelLevel() {
        return level;
    }

    public boolean isModelSpecialFruit() {
        return specialFruit;
    }

    public void setModelSpecialFruit(boolean specialFruit) {
        this.specialFruit = specialFruit;
    }

    public Player getModelPlayer() {
        return player;
    }

    public double getModelTimelineDuration() {
        return timelineDuration;
    }

    public void setModelTimelineDuration(double timelineDuration) {
        this.timelineDuration = timelineDuration;
    }
}
