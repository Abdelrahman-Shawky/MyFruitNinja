package Command;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SliceSound2 {

    private MediaPlayer mediaPlayer;

    public SliceSound2(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

    }

    public void playSliceSound(){

        String sliceString = new File("slice2.mp3").toURI().toString();
        mediaPlayer = new MediaPlayer(new Media(sliceString));
        mediaPlayer.play();

    }


}
