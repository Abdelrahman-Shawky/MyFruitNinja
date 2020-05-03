package Objects;

import javafx.scene.image.ImageView;

public interface GameObject {

//    ENUM getObjectType ();
//    public int getXlocation ();
//    public int getYlocation ();
//    public int getMaxHeight ();
    public double getVelocity();
//    public int getFallingVelocity ();
    public Boolean isSliced ();
//    public Boolean hasMovedOffScreen ();
//    public void setMovedOffScreen();
    public void slice ();
//    public void move (double time);
    public boolean isBomb();
    public boolean isFatal();
    public ImageView getBufferedImages ();
    public boolean isSpecialFruit1();
    public boolean isSpecialFruit2();


}
