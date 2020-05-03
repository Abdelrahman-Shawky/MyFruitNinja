package SavingAdapters;

import Memento.GameMementoXML;

public class GameMementoToXMLAdapter extends GameMementoXML {

    public GameMementoToXMLAdapter(int crossCount,int level,int score,int round,double timelineDuration,int timerMinutes,int timerSecond) {
        setCrossCount(crossCount);
        setLevel(level);
        setScore(score);
        setRound(round);
        setTimelineDuration(timelineDuration);
        setTimerMinutes(timerMinutes);
        setTimerSeconds(timerSecond);
    }
}
