package Memento;

import FruitNinja.Player;

public class GameMemento {

    private int crossCount;
    private int round;
    private int timerMinutes;
    private int timerSeconds;
    private int level;
    private Player player;
    private double timelineDuration;


    public GameMemento(int crossCount, int round, int timerMinutes, int timerSeconds, int level, Player player, double timelineDuration) {
        this.crossCount = crossCount;
        this.round = round;
        this.timerMinutes = timerMinutes;
        this.timerSeconds = timerSeconds;
        this.level = level;
        this.player = player;
        this.timelineDuration = timelineDuration;
    }

    public int getCrossCount() {
        return crossCount;
    }

    public int getRound() {
        return round;
    }

    public int getTimerMinutes() {
        return timerMinutes;
    }

    public int getTimerSeconds() {
        return timerSeconds;
    }

    public int getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    public double getTimelineDuration() {
        return timelineDuration;
    }

}
