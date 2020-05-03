package Memento;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gameMemento")
public class GameMementoXML {

    private int crossCount;
    private int round;
    private int timerMinutes;
    private int timerSeconds;
    private int level;
    private int score;
    private double timelineDuration;


    @XmlElement(name = "crossCount")
    public int getCrossCount() {
        return crossCount;
    }

    @XmlElement(name = "round")
    public int getRound() {
        return round;
    }

    @XmlElement(name = "timerMinutes")
    public int getTimerMinutes() {
        return timerMinutes;
    }

    @XmlElement(name = "timerSeconds")
    public int getTimerSeconds() {
        return timerSeconds;
    }

    @XmlElement(name = "level")
    public int getLevel() {
        return level;
    }

    @XmlElement(name = "timelineDuration")
    public double getTimelineDuration() {
        return timelineDuration;
    }

    @XmlElement(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCrossCount(int crossCount) {
        this.crossCount = crossCount;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setTimerMinutes(int timerMinutes) {
        this.timerMinutes = timerMinutes;
    }

    public void setTimerSeconds(int timerSeconds) {
        this.timerSeconds = timerSeconds;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTimelineDuration(double timelineDuration) {
        this.timelineDuration = timelineDuration;
    }
}
