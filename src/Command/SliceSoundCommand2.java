package Command;

public class SliceSoundCommand2 implements Command{

    private SliceSound2 sliceSound2;

    public SliceSoundCommand2(SliceSound2 sliceSound2) {
        this.sliceSound2 = sliceSound2;
    }

    @Override
    public void execute() {
        sliceSound2.playSliceSound();

    }

}
