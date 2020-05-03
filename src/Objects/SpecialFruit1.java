package Objects;

import States.FruitState;
import States.SlicedState;
import States.UnslicedState;
import javafx.scene.image.ImageView;

public class SpecialFruit1 implements GameObject {

    private FruitState fruitState;

    private boolean sliced;
    private boolean bomb;
    private double velocity;

    public SpecialFruit1() {
        this.sliced = false;
        this.bomb = false;
        this.velocity = 0.95;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public Boolean isSliced() {
        return sliced;    }

    @Override
    public void slice() {
        this.sliced = true;
    }

    @Override
    public boolean isBomb() {
        return bomb;
    }

    @Override
    public boolean isFatal() {
        return false;
    }

    @Override
    public ImageView getBufferedImages() {

        if(!isSliced())
            fruitState = new UnslicedState();
        else
            fruitState = new SlicedState();

        ImageView imageView = fruitState.getBufferedImages("banana.png","bananaPieces.png");

        imageView.setFitWidth(150);
        imageView.setFitHeight(100);

        return imageView;
    }

    @Override
    public boolean isSpecialFruit1() {
        return true;
    }

    @Override
    public boolean isSpecialFruit2() {
        return false;
    }
}
