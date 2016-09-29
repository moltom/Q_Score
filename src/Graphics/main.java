package Graphics;

import Data.Controller;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;

public class main extends Application
{
    public static final int SIZE_X = 400;
    private final int SIZE_Y = 300;
    private final double TITLE_FONT_SIZE = 36;
    private final double BUTTON_FONT_SIZE = 18;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Set title of window
        primaryStage.setTitle("Q is Silqent Scoring");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:src/Graphics/favicon.ico"));

        //button.setOnAction(e -> System.out.println("Lambda expression"));
        //object.setOnAction(event_handler);

        //---OBJECTS IN WINDOW---
        Label Title = new Label("Scoring Application");
        Title.setFont(Font.font(TITLE_FONT_SIZE));

        Button score_menu_button = new Button("Enter Scores");
        score_menu_button.setFont(Font.font(BUTTON_FONT_SIZE));
        score_menu_button.setOnAction(e -> {
            //Open Scoring window
            TeamWindow.display();
        });

        Button analyze_menu_button = new Button("Analyze Data");
        analyze_menu_button.setFont(Font.font(BUTTON_FONT_SIZE));
        //@TODO Add analyze window GUI
        analyze_menu_button.setOnAction(e -> {
            //Open analyze menu for scores
            //Temporary missing window
            AlertBox.display("Error", "Analyze menu not created");
        });

        Button debugButton = new Button("Debug");
        debugButton.setFont(Font.font(BUTTON_FONT_SIZE));
        debugButton.setOnAction(e -> {
            Scanner sc = new Scanner(System.in);
            System.out.print("Command: >");
            String input = sc.nextLine();
            //System.out.println(input);
            //@TODO Add more console commands for debugging
            switch(input.substring(0,3))
            {
                case "rmv":
                    if(ConfirmBox.display("","Are you sure you want to delete ALL data?")){
                        Controller.clearDataFiles();
                    }
                    break;
                case "tst":
                    if(input.substring(4).equals("write"))
                    {
                        String uslessData;
                        System.out.println("-BEGIN WRITE TEST-");
                        System.out.println("writing file with saveMatchData()...");
                        Controller.saveMatchData(1);
                        System.out.print("(Press enter to continue)");
                        uslessData = sc.nextLine();
                        System.out.println("writing file with saveData...");
                        Controller.writeData("TEST,TEST,TEST", "matches");
                        Controller.writeData("TEST2,TEST2,TEST2", "teams");
                        uslessData = sc.nextLine();
                        System.out.println("-END WRITE TEST-");
                    }
                    break;
                case "hia":
                    System.out.println("Fuck you");
                    break;
            }
        });

        Button menu_exit_button = new Button("Exit");
        menu_exit_button.setFont(Font.font(BUTTON_FONT_SIZE));
        menu_exit_button.setOnAction(e -> {
            //Exit program
            primaryStage.close();
        });
        //----------------------

        //Layout manager
        VBox layout = new VBox(10);
        layout.getChildren().addAll(Title, score_menu_button, analyze_menu_button, debugButton, menu_exit_button);
        layout.setAlignment(Pos.CENTER);

        //Create new scene
        Scene scene = new Scene(layout, SIZE_X, SIZE_Y);
        primaryStage.setScene(scene);

        //Display final window
        primaryStage.show();
    }
}
