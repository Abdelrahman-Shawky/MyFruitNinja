package Command;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ExplosionSound {

    private MediaPlayer mediaPlayer;

    public ExplosionSound(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playExplosionSound(){

        String explosionString = new File("explosion.mp3").toURI().toString();
        mediaPlayer = new MediaPlayer(new Media(explosionString));
        mediaPlayer.play();
    }
}
