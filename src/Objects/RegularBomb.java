package Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RegularBomb implements GameObject{

    private boolean sliced;
    private boolean bomb;
    private double velocity;

    public RegularBomb() {
        this.sliced = false;
        this.bomb = true;
        this.velocity = 1.15;
    }

    @Override
    public boolean isFatal() {
        return false;
    }

    @Override
    public boolean isBomb() {
        return bomb;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public Boolean isSliced() {
        return sliced;
    }

    @Override
    public void slice() {
        this.sliced = true;

    }

    @Override
    public ImageView getBufferedImages() {

        FileInputStream input = null;
        try {
            input = new FileInputStream("regularBomb.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image= new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        return imageView;
    }

    @Override
    public boolean isSpecialFruit1() {
        return false;
    }

    @Override
    public boolean isSpecialFruit2() {
        return false;
    }
}
