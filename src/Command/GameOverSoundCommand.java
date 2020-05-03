package Command;

public class GameOverSoundCommand implements Command{

    private GameOverSound gameOverSound;

    public GameOverSoundCommand(GameOverSound gameOverSound) {
        this.gameOverSound = gameOverSound;
    }

    @Override
    public void execute() {
        gameOverSound.playSliceSound();

    }

}
