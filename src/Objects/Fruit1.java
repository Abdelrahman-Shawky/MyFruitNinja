package Objects;

import States.FruitState;
import States.SlicedState;
import States.UnslicedState;
import javafx.scene.image.ImageView;

public class Fruit1 implements GameObject {

    private FruitState fruitState;

    private boolean sliced;
    private boolean bomb;
    private double velocity;


//    public Apple(int x, int y, boolean sliced, boolean movedOffScreen) {
//        this.x = x;
//        this.y = y;
//        this.sliced = sliced;
//        this.movedOffScreen = movedOffScreen;
//    }

    public Fruit1() {
        this.sliced = false;
        this.bomb = false;
        this.velocity = 1;
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

        return fruitState.getBufferedImages("apple.png","applePieces.png");
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
