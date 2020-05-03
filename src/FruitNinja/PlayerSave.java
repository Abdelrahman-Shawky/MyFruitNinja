package FruitNinja;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "playerSave")
public class PlayerSave {

    private int easyHighScore;
    private int mediumHighScore;
    private int hardHighScore;
    private int arcadeHighScore;



    @XmlElement(name = "EasyHighScore")
    public int getEasyHighScore() {
        return easyHighScore;
    }

    @XmlElement(name = "MediumHighScore")
    public int getMediumHighScore() {
        return mediumHighScore;
    }

    @XmlElement(name = "HardHighScore")
    public int getHardHighScore() {
        return hardHighScore;
    }

    @XmlElement(name = "ArcadeHighScore")
    public int getArcadeHighScore() {
        return arcadeHighScore;
    }

    public void setEasyHighScore(int easyHighScore) {
        this.easyHighScore = easyHighScore;
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
}
