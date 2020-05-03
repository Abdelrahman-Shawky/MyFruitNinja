package Objects;

import States.FruitState;
import States.SlicedState;
import States.UnslicedState;
import javafx.scene.image.ImageView;

public class Fruit3 implements GameObject{

    private FruitState fruitState;

    private boolean sliced;
    private boolean bomb;
    private double velocity;

    public Fruit3() {
        this.sliced = false;
        this.bomb = false;
        this.velocity = 1.1;
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

        if(!isSliced())
            fruitState = new UnslicedState();
        else
            fruitState = new SlicedState();
        ImageView imageview = fruitState.getBufferedImages("watermelon.png","watermelonPieces.png");
        imageview.setFitWidth(225);
        imageview.setFitHeight(125);

        return imageview;
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
