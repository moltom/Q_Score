package Graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class analyzeWindow
{
    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;
    private static Stage window = new Stage();

    static void display()
    {
        //Setting title and size
        window.setTitle("Scoring");
        window.setMinWidth(WIDTH);
        window.setMinHeight(HEIGHT);
        window.getIcons().add(new Image("file:src/Graphics/favicon.ico"));

        Scene mainContents = mainList();

        window.show();
    }


    private static Scene mainList()
    {
        BorderPane contents = new BorderPane();
        //contents.setLeft(leftSide());

        return new Scene(contents);
    }

    /*
    private static VBox leftSide()
    {

    }*/

    private static ListView<String> list(int width, int height){
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        list.setItems(items);

        list.setPrefWidth(width);
        list.setPrefHeight(height);

        return list;
    }
}
