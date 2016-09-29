package Graphics;

import Data.Controller;
import Utilities.util;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static Utilities.util.parse;

public class scoringWindow
{
    private static final int WIDTH = 450;
    private static final int HEIGHT = 500;
    private static final int FONT_SIZE = 20;
    private static final int VERTICAL_SPACING = 6;

    private static Stage window  = new Stage();
    private static Scene Autonomous;
    private static Scene TeleOP;

    public static void display()
    {
        //Block any events from happening until window is accounted for
        //window.initModality(Modality.APPLICATION_MODAL);

        //Setting title and size
        window.setTitle("Scoring");
        window.setMinWidth(WIDTH);
        window.setMinHeight(HEIGHT);
        window.setResizable(false);
        window.getIcons().add(new Image("file:src/Graphics/favicon.ico"));

        //Setup scenes
        Autonomous = Auto();
        TeleOP = Tele();

        //Default starting mode
        window.setScene(Autonomous);

        //Display and wait for action.
        window.show();
    }

    /**
     * Method to create a counter GUI to count up or down
     * @param text The text to display on the Label, includes a default value
     * @param type The type of data it should increment in the data controller
     * @return A GUI HBox to add to a larger scene
     */
    private static HBox Counter(String text, String type, int teamNum, int limit)
    {
        HBox contents = new HBox();

        //Label
        Label box = new Label(text);
        box.setMinHeight(20);

        //Plus button
        Button plus = new Button("+");
        plus.setMinWidth(20);
        plus.setMinHeight(20);
        plus.setOnAction(e -> {
            //Gets current count value on counter and updates it
            int length = text.length() - 1;
            String end = box.getText().substring(length);
            String beg = text.substring(0, length);
            int newNumber = util.parse(end) + 1;

            if(newNumber <= limit || limit == 0) {
                box.setText(beg + newNumber);
                Controller.addData(teamNum, type, 1);
            }
        });

        //Minus button
        Button minus = new Button("-");
        minus.setMinWidth(25);
        minus.setMinHeight(20);
        minus.setOnAction(e -> {
            //Gets current count value on counter and updates it
            int length = text.length() - 1;
            String end = box.getText().substring(length);
            String beg = text.substring(0, length);
            int newNumber = parse(end) - 1;

            if(newNumber <= limit || limit == 0) {
                box.setText(beg + newNumber);
                Controller.addData(teamNum, type, -1);
            }
        });

        contents.getChildren().addAll(plus, minus, box);
        contents.setSpacing(3);
        contents.setAlignment(Pos.CENTER_LEFT);
        return contents;
    }

    private static HBox Header()
    {
        HBox header = new HBox();
        //Setting padding between parts
        header.setPadding(new Insets(15, 12, 15, 0)); //(top/right/bottom/left)
        header.setSpacing(3);

        //Save_match, Auto, Tele, Exit

        //Save match score button
        Button saveMatch = new Button("Save Match");
        saveMatch.setOnAction(e -> {
            if(Controller.matchNumber  != 0) {
                Controller.saveMatchData(Controller.matchNumber);
                if(ConfirmBox.display("", "Match data saved. Exit scoring?"))
                    window.close();
            }
            else {
                AlertBox.display("", "Missing or invalid match number (see Header())");
            }
        });

        //Autonomous scene change button
        Button autoButton = new Button("Autonomous");
        autoButton.setOnAction(e -> {
            if(!window.getScene().equals(Autonomous))
                window.setScene(Autonomous);
            else
                AlertBox.display("Error", "Already in autonomous");
        });

        //Tele op scene change button
        Button teleButton = new Button("Tele-Op");
        teleButton.setOnAction(e -> {
            if(!window.getScene().equals(TeleOP))
                window.setScene(TeleOP);
            else
                AlertBox.display("Error", "Already in tele-op");
        });

        //Close window button
        Button close = new Button("Close");
        close.setOnAction(e -> {
            if(Controller.hasData()) {
                if (ConfirmBox.display("", "Are you sure you want to exit?")) {
                    window.close();
                    Controller.resetData();
                }
            }
            else {
                window.close();
            }
        });

        //Add all elements to layout
        header.getChildren().addAll(saveMatch, autoButton, teleButton, close);

        return header;
    }

    private static CheckBox Checkbox(String text, String type, int teamNum)
    {
        CheckBox check = new CheckBox(text);
        check.setOnAction(e -> {
            if(check.isSelected())
                Controller.addData(teamNum, type, 1);
            else
                Controller.addData(teamNum, type, -1);
        });
        check.setAlignment(Pos.CENTER);

        return check;
    }

    private static VBox autoContent(int teamNumber)
    {
        int subWidth = WIDTH / 2;

        VBox VB = new VBox();
        VB.setSpacing(VERTICAL_SPACING);
        VB.setMinWidth(subWidth);

        Label TEAM = new Label(" "+teamNumber);
        TEAM.setFont(Font.font(FONT_SIZE));

        //COUNTERS
        HBox ct1 = Counter("High: 0", "autoHighParticle", teamNumber, 0);
        HBox ct2 = Counter("Ramp: 0", "autoLowParticle", teamNumber, 0);
        //@TODO Make it so it removes beacons from the other team
        HBox ct3 = Counter("Beacons: 0", "autoBeacons", teamNumber, 4);

        //BLACK LINE
        Rectangle line = new Rectangle((double) subWidth, 1.0);

        //RADIO BUTTONS
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Not Parked");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setOnAction(e -> {
            Controller.addData(teamNumber, "parkPartial", -1);
            Controller.addData(teamNumber, "parkFull", -1);
        });

        RadioButton rb2 = new RadioButton("Partial Park");
        rb2.setToggleGroup(group);
        rb2.setOnAction(e -> {
            Controller.addData(teamNumber, "parkPartial", 1);
            Controller.addData(teamNumber, "parkFull", -1);
        });

        RadioButton rb3 = new RadioButton("Full Park");
        rb3.setToggleGroup(group);
        rb3.setOnAction(e -> {
            Controller.addData(teamNumber, "parkPartial", -1);
            Controller.addData(teamNumber, "parkFull", 1);
        });

        //Checkbox #3 for cap ball
        CheckBox cb3 = Checkbox("Cap Ball Moved","autoCapBall", teamNumber);

        VB.getChildren().addAll(TEAM,ct1,ct2,ct3,line,cb3,rb1,rb2,rb3);
        VB.setAlignment(Pos.CENTER_LEFT);
        return VB;
    }

    private static Scene Auto()
    {
        int subWidth = WIDTH / 2;

        //Main content layout for entire scene
        VBox mainContent = new VBox();

        HBox head = Header();

        VBox cont1 = autoContent(Controller.getTeamNumbers()[0]);
        cont1.setMaxWidth(subWidth);
        VBox cont2 = autoContent(Controller.getTeamNumbers()[1]);
        cont2.setMaxWidth(subWidth);

        HBox content = new HBox();
        content.getChildren().addAll(cont1,cont2);

        mainContent.setPadding(new Insets(10, 10, 10, 10)); //(top/right/bottom/left)
        mainContent.getChildren().addAll(head, content);
        return new Scene(mainContent);
    }


    private static VBox teleContent(int teamNumber)
    {
        int subWidth = WIDTH / 2;

        VBox VB = new VBox();
        VB.setSpacing(VERTICAL_SPACING);
        VB.setMinWidth(subWidth);

        //CONTENT
        Label tLab = new Label(" "+teamNumber);
        tLab.setFont(Font.font(FONT_SIZE));

        HBox ct1 = Counter("High: 0", "highParticle", teamNumber, 0);
        HBox ct2 = Counter("Ramp: 0", "lowParticle", teamNumber, 0);
        HBox ct3 = Counter("Beacons: 0", "beacon", teamNumber, 4);
        Rectangle line = new Rectangle((double) subWidth, 1.0);

        //Cap ball heights
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("No Cap");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setOnAction(e -> {
            Controller.addData(teamNumber, "capLow", -1);
            Controller.addData(teamNumber, "capCross", -1);
            Controller.addData(teamNumber, "capHigh", -1);
        });

        RadioButton rb2 = new RadioButton("Raised Cap");
        rb2.setToggleGroup(group);
        rb2.setOnAction(e -> {
            Controller.addData(teamNumber, "capLow", 1);
            Controller.addData(teamNumber, "capCross", -1);
            Controller.addData(teamNumber, "capHigh", -1);
        });

        RadioButton rb3 = new RadioButton("Crossbar Cap");
        rb3.setToggleGroup(group);
        rb3.setOnAction(e -> {
            Controller.addData(teamNumber, "capLow", -1);
            Controller.addData(teamNumber, "capCross", 1);
            Controller.addData(teamNumber, "capHigh", -1);
        });

        RadioButton rb4 = new RadioButton("Full cap");
        rb4.setToggleGroup(group);
        rb4.setOnAction(e -> {
            Controller.addData(teamNumber, "capLow", -1);
            Controller.addData(teamNumber, "capCross", -1);
            Controller.addData(teamNumber, "capHigh", 1);
        });
        //-------

        VB.getChildren().addAll(tLab, ct1, ct2, ct3, line, rb1, rb2, rb3, rb4);
        VB.setAlignment(Pos.CENTER_LEFT);
        return VB;
    }


    private static Scene Tele()
    {
        int subWidth = WIDTH / 2;

        //Main content layout for entire scene
        VBox mainContent = new VBox();

        HBox head = Header();

        VBox cont1 = teleContent(Controller.getTeamNumbers()[0]);
        cont1.setMaxWidth(subWidth);
        VBox cont2 = teleContent(Controller.getTeamNumbers()[1]);
        cont2.setMaxWidth(subWidth);

        HBox content = new HBox();
        content.getChildren().addAll(cont1,cont2);

        mainContent.setPadding(new Insets(10, 10, 10, 10)); //(top/right/bottom/left)
        mainContent.getChildren().addAll(head, content);
        return new Scene(mainContent);
    }
}
