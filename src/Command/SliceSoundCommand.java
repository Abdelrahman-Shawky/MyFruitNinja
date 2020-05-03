package Command;

public class SliceSoundCommand implements Command {

    private SliceSound sliceSound;

    public SliceSoundCommand(SliceSound sliceSound) {
        this.sliceSound = sliceSound;
    }

    @Override
    public void execute() {
        sliceSound.playSliceSound();

    }
}
