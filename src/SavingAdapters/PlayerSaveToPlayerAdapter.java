package SavingAdapters;

import FruitNinja.Player;
import FruitNinja.PlayerSave;

public class PlayerSaveToPlayerAdapter extends PlayerSave {

    private Player player;

    public PlayerSaveToPlayerAdapter(int easyHighScore, int mediumHighScore, int hardHighScore, int arcadeHighScore) {
        this.player = Player.getInstance(easyHighScore,mediumHighScore,hardHighScore,arcadeHighScore);
    }

    public Player getPlayer() {
        return player;
    }
}
