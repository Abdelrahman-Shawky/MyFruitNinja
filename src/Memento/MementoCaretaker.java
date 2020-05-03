package Memento;

public class MementoCaretaker {

    private GameMemento gameMemento;

    public void createMemento(GameMemento gameMemento){
        this.gameMemento = gameMemento;
    }

    public GameMemento getMemento(){
        return gameMemento;
    }

}
