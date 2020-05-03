package FruitNinja;


import Memento.GameMementoXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "save")
@XmlAccessorType(XmlAccessType.FIELD)
public class Save {

    private PlayerSave playerSave = null;
    private GameMementoXML gameMemento = null;

    public PlayerSave getPlayerSave() {
        return playerSave;
    }

    public void setPlayerSave(PlayerSave playerSave) {
        this.playerSave = playerSave;
    }

    public GameMementoXML getGameMemento() {
        return gameMemento;
    }

    public void setGameMemento(GameMementoXML gameMemento) {
        this.gameMemento = gameMemento;
    }
}
