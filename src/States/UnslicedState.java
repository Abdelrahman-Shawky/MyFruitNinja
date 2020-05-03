package States;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UnslicedState implements FruitState{

    @Override
    public ImageView getBufferedImages(String photo, String photoPieces) {
        FileInputStream input = null;

        try {
            input = new FileInputStream(photo);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image= new Image(input);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(225);
        imageView.setFitHeight(100);




        return imageView;
    }
}
