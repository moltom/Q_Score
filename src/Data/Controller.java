package Data;

import java.io.*;
import java.util.Scanner;

import Graphics.AlertBox;

public class Controller
{
    //Needs to use constructors to create blank slate
    private static Team[] team = {new Team(), new Team(), new Team(), new Team()};
    public static int matchNumber = 0;

    /**
     * Sets up the temporary team objects with team numbers for the addData()
     * call.
     * @param teamNums An array of team numbers in order of red1,red2,blue1,blue2
     */
    public static void setupTeams(int[] teamNums)
    {
        for(int i = 0;i < teamNums.length;i++)
            team[i].setTeam_number(teamNums[i]);
    }

    public static void setMatchNumber(int num)
    {
        matchNumber = num;
    }

    /**
     * Gets the team numbers setup from the window
     * @return The team numbers in an integer array
     */
    public static int[] getTeamNumbers(){
        return new int[] {team[0].getTeam_number(), team[1].getTeam_number(),
                team[2].getTeam_number(), team[3].getTeam_number()
        };
    }

    /**
     * Method that returns true if any of the team objects have bare data
     * @return True if any team has a team number listed
     */
    public static boolean hasData() {
        for(Team d : team)
            if(d.getTeam_number() != 0)
                return true;
        return false;
    }

    /**
     * Resets all saved team data in temp team objects
     */
    public static void resetData()
    {
        for(Team d : team)
            d.reset();
    }

    /**
     * Adds data points to the temporary team objects during scoring. This
     * allows the data to be constantly modified in RAM before being written
     * to file.
     * @param team_number The team number in which to modify the data for
     * @param type The type of data that should be added.
     */
    public static void addData(int team_number, String type, int quantity)
    {
        //System.out.println("addData() called for "+team_number+","+type+" for "+quantity);
        if(quantity == 0) {
            AlertBox.display("Error", "Invalid quantity of 0 (see Controller class)");
            return;
        }
        for(Team d : team) {
            if(d.getTeam_number() == team_number) {
                System.out.println("Adding "+quantity+" of "+type+" to team "+team_number);
                switch(type) {
                    case "highParticle":
                        d.setHighParticle(d.getHighParticle() + quantity);
                        d.setPoints(d.getPoints() + (quantity * 5));
                        break;
                    case "lowParticle":
                        d.setLowParticle(d.getLowParticle() + quantity);
                        d.setPoints(d.getPoints() + (quantity));
                        break;
                    case "beacon":
                        d.setBeacon(d.getBeacon() + quantity);
                        d.setPoints(d.getPoints() + (quantity * 10));
                        break;
                    case "capLow":
                        d.setCapLow(quantity > 0);
                        d.setPoints(d.getPoints() + (quantity * 10));
                        break;
                    case "capCross":
                        d.setCapCross(quantity > 0);
                        d.setPoints(d.getPoints() + (quantity * 20));
                        break;
                    case "capHigh":
                        d.setCapHigh(quantity > 0);
                        d.setPoints(d.getPoints() + (quantity * 40));
                        break;
                    case "parkPartial":
                        d.setParkPartial(quantity > 0);
                        d.setPoints(d.getPoints() + (quantity * 5));
                        break;
                    case "parkFull":
                        d.setParkFull(quantity > 0);
                        d.setPoints(d.getPoints() + (quantity * 10));
                        break;
                    case "autoCapBall":
                        d.setAutoCap(quantity > 0);
                        d.setPoints(d.getPoints() + (quantity * 5));
                        break;
                    case "autoHighParticle":
                        d.setAutoHighParticle(d.getAutoHighParticle() + quantity);
                        d.setPoints(d.getPoints() + (quantity * 15));
                        break;
                    case "autoLowParticle":
                        d.setAutoLowParticle(d.getAutoLowParticle() + quantity);
                        d.setPoints(d.getPoints() + (quantity * 5));
                        break;
                    case "autoBeacons":
                        d.setAutoBeacons(d.getAutoBeacons() + quantity);
                        d.setPoints(d.getPoints() + (quantity * 30));
                        break;
                    default:
                        AlertBox.display("Error", "Invalid data type \""+type + "\" (see Controller class)");
                        break;
                }
                return; //End method
            }
        }
    }

    private static String formatString(String... input)
    {
        String output = "";
        for(String d : input) {
            output += d;
        }
        return output.substring(0,output.length()-1);
    }

    private static String its(int in)
    {
        return "" + in + ",";
    }

    /**
     * Clears all data from both team and match files. ALL DATA.
     */
    //@TODO Add the default strings into the file
    public static void clearDataFiles()
    {
        String clearedData = "";
        try{
            FileWriter fileWriter1 = new FileWriter(new File("src\\Data\\teams.csv"), false);
            fileWriter1.append(clearedData);
            FileWriter fileWriter2 = new FileWriter(new File("src\\Data\\matches.csv"), false);
            fileWriter2.append(clearedData);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMatchData(int match)
    {
        if(!hasData()) {
            AlertBox.display("Error", "NO DATA TO SAVE (See saveMatchData())");
            return;
        }
        int[] teamNum = getTeamNumbers();
        String data;
        data = formatString(its(match),its(teamNum[0]),its(teamNum[1]),its(teamNum[2]),its(teamNum[3]),
                team[0].toString(),team[1].toString(),team[2].toString(),team[3].toString());

        //Main data declaration
        writeData(data.substring(0,data.length() - 1), "matches");
    }

    public static String[] readData(int line, String inFileName)
    {
        String cvsSplitBy = ",";
        String fileName = inFileName.equals("teams") ? "src/Data/teams.csv" : "src/Data/matches.csv";
        File file = new File(fileName);
        int counter = 0;

        try {
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                if(counter == line) {
                    return inputStream.next().split(cvsSplitBy);
                }
            }
            inputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeData(String data, String inFileName)
    {
        String fileName = inFileName.equals("teams") ? "src/Data/teams.csv" : "src/Data/matches.csv";
        File file = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            StringBuilder sb = new StringBuilder();
            sb.append(data);
            sb.append("\n");
            fileWriter.append(sb.toString());

            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
