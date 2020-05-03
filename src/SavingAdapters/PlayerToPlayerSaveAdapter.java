package SavingAdapters;

import FruitNinja.PlayerSave;

public class PlayerToPlayerSaveAdapter extends PlayerSave {

    public PlayerToPlayerSaveAdapter(int easyHighScore, int mediumHighScore, int hardHighScore, int arcadeHighScore) {
        setEasyHighScore(easyHighScore);
        setMediumHighScore(mediumHighScore);
        setHardHighScore(hardHighScore);
        setArcadeHighScore(arcadeHighScore);

    }
}
