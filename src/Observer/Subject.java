package Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer){
        this.observers.add(observer);
    }

    public void notifyObservers(int score){
        for(Observer observer : observers){
            observer.update(score);
        }
    }

    public void notifyObservers(int timerMinutes,int timerSeconds,int highScore){
        for(Observer observer : observers){
            observer.update(timerMinutes,timerSeconds,highScore);
        }
    }

    public void notifyObservers(int timerMinutes,int timerSeconds,int highScore,int count){
        for(Observer observer : observers){
            observer.update(timerMinutes,timerSeconds,highScore,count);
        }
    }

    public void notifyObservers(int timerMinutes,int timerSeconds,int highScore,int count,boolean cross){
        for(Observer observer : observers){
            observer.update(timerMinutes,timerSeconds,highScore,count,cross);
        }
    }



}
