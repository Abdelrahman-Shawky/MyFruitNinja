package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    private boolean answer;

    public boolean message(String title,String message){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        Label label = new Label(message);
        label.setFont(new Font("Forte",35));
        label.setTextFill(Color.RED);

        Button yes = new Button("YES");
        yes.setFont(new Font("Forte",35));
        yes.setStyle("-fx-background-radius: 15 ");

        Button no = new Button("NO");
        no.setFont(new Font("Forte",35));
        no.setStyle("-fx-background-radius: 15 ");

        yes.setOnAction(e -> {
            answer = true;
            stage.close();
        });

        no.setOnAction(e -> {
            answer = false;
            stage.close();
        });

        HBox box = new HBox(15);
        box.getChildren().add(yes);
        box.getChildren().add(no);
        box.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(label,box);
        vBox.setAlignment(Pos.CENTER);


        Scene scene = new Scene(vBox,650,200);
        stage.setScene(scene);
        stage.showAndWait();
        return answer;
    }


}
