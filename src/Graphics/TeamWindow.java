package Graphics;

import Data.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Utilities.util.parse;

public class TeamWindow
{
    public static void display()
    {
        //Main window
        Stage teamWindow = new Stage();

        //teamWindow.initModality(Modality.APPLICATION_MODAL);
        teamWindow.setTitle("Teams");
        teamWindow.setMinWidth(250);
        teamWindow.setMinHeight(200);
        teamWindow.setResizable(false);
        teamWindow.getIcons().add(new Image("file:src/Graphics/favicon.ico"));

        //-Team Boxes-
        VBox left = new VBox();
        Label redLabel = new Label("RED");
        left.setAlignment(Pos.CENTER);
        TextField teamR1 = new TextField("Red 1");
        TextField teamR2 = new TextField("Red 2");
        left.getChildren().addAll(redLabel, teamR1, teamR2);

        VBox right = new VBox();
        Label blueLabel = new Label("BLUE");
        right.setAlignment(Pos.CENTER);
        TextField teamB1 = new TextField("Blue 1");
        TextField teamB2 = new TextField("Blue 2");
        right.getChildren().addAll(blueLabel, teamB1, teamB2);

        HBox teamHBox = new HBox();
        teamHBox.getChildren().addAll(left, right);
        //------------

        //MATCH NUMBER
        TextField matchNumber = new TextField("Match");
        matchNumber.setMaxWidth(65);
        //------------

        //Buttons
        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(e -> {
            //Save data
            boolean cont = true;
            int[] teams = {
                    parse(teamR1.getText()),
                    parse(teamR2.getText()),
                    parse(teamB1.getText()),
                    parse(teamB2.getText())
            };
            //Check validity
            for(int in : teams) {
                if(in == 0) {
                    cont = false;
                    AlertBox.display("","One or more of the team numbers are invalid");
                    break;
                }
            }
            if(parse(matchNumber.getText()) == 0)
            {
                cont = false;
                AlertBox.display("","Invalid match number");
            }
            if(cont) {
                Controller.setupTeams(teams);
                Controller.setMatchNumber(parse(matchNumber.getText()));
                scoringWindow.display();
                teamWindow.close();
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            teamWindow.close();
        });
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(acceptButton, cancelButton);
        //-------

        VBox main = new VBox();
        main.getChildren().addAll(matchNumber,teamHBox,buttonBox);
        main.setAlignment(Pos.CENTER);
        Scene teamList = new Scene(main);

        teamWindow.setScene(teamList);
        teamWindow.show();
    }
}
