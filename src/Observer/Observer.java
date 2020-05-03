package Observer;

public interface Observer {

    public void update(int score);
    public void update(int timerMinutes,int timerSeconds,int highScore);
    public void update(int timerMinutes,int timerSeconds,int highScore,int count);
    public void update(int timerMinutes,int timerSeconds,int highScore,int count,boolean cross);

}
