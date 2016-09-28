package Graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox
{
    static boolean answer = false;

    public static boolean display(String title, String message)
    {
        Stage window  = new Stage();

        //Block any events from happening until window is accounted for
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250.0);

        Label label = new Label(message);

        //Buttons
        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button no = new Button("No");
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //Layouts
        VBox layout =  new VBox(10);
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        //Add all elements to layout
        buttons.getChildren().addAll(yes, no);
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);

        //Add scene with layout to the window
        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Display and wait for action.
        window.showAndWait();

        return answer;
    }
}
