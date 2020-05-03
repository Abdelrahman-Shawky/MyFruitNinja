package Command;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SliceSound {

    private MediaPlayer mediaPlayer;

    public SliceSound(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

    }

    public void playSliceSound(){

        String sliceString = new File("slice.mp3").toURI().toString();
        mediaPlayer = new MediaPlayer(new Media(sliceString));
        mediaPlayer.play();

    }


}
