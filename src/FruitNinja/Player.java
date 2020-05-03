package FruitNinja;

import Observer.Subject;


public class Player extends Subject {

    private int score;
    private int crossCount;
    private int easyHighScore;
    private int mediumHighScore;
    private int hardHighScore;
    private int arcadeHighScore;


    private static Player instance;
    private Player(int easyHighScore, int mediumHighScore, int hardHighScore, int arcadeHighScore) {
        super();
        this.easyHighScore = easyHighScore;
        this.mediumHighScore = mediumHighScore;
        this.hardHighScore = hardHighScore;
        this.arcadeHighScore = arcadeHighScore;
        this.crossCount = 0;
        this.score = 0;
    }

    public static Player getInstance(int easyHighScore, int mediumHighScore, int hardHighScore, int arcadeHighScore){
        if(instance==null)
            return instance = new Player(easyHighScore, mediumHighScore, hardHighScore, arcadeHighScore);
        else
            return instance;
    }

    public void setScorePlus() {
        this.score++;
        notifyObservers(score);
    }

    public int getEasyHighScore() {
        return easyHighScore;
    }

    public void setEasyHighScore(int easyHighScore) {
        this.easyHighScore = easyHighScore;
    }

    public int getScore() {
        return score;
    }

    public int getMediumHighScore() {
        return mediumHighScore;
    }

    public int getHardHighScore() {
        return hardHighScore;
    }

    public int getArcadeHighScore() {
        return arcadeHighScore;
    }

    public void setMediumHighScore(int mediumHighScore) {
        this.mediumHighScore = mediumHighScore;
    }

    public void setHardHighScore(int hardHighScore) {
        this.hardHighScore = hardHighScore;
    }

    public void setArcadeHighScore(int arcadeHighScore) {
        this.arcadeHighScore = arcadeHighScore;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
