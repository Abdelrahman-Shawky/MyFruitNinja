package Command;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BuzzerSound {

    private MediaPlayer mediaPlayer;

    public BuzzerSound(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playBuzzerSound(){

        String buzzerString = new File("buzzer.mp3").toURI().toString();
        mediaPlayer = new MediaPlayer(new Media(buzzerString));
        mediaPlayer.play();
    }
}
