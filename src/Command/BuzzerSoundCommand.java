package Command;

public class BuzzerSoundCommand implements Command{

    private BuzzerSound buzzerSound;

    public BuzzerSoundCommand(BuzzerSound buzzerSound) {
        this.buzzerSound = buzzerSound;
    }

    @Override
    public void execute() {
        buzzerSound.playBuzzerSound();
    }
}
