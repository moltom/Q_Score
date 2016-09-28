package Graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox
{
    public static void display(String title, String message)
    {
        Stage window  = new Stage();

        //Block any events from happening until window is accounted for
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(120);

        Label label = new Label(message);
        Button close = new Button("Okay");
        //Lambda close
        close.setOnAction(e -> window.close());

        VBox layout =  new VBox(10);
        //Add all elements to layout
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        //Add scene with layout to the window
        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Display and wait for action.
        window.showAndWait();
    }
}
