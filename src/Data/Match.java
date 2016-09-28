package Data;

public class Match
{
    int matchNumber;
    Team[] Teams;

    public Match(int Num, Team[] teams)
    {
        matchNumber = Num;

        //Add teams
        if(teams.length != 4)
        {
            System.out.println("INVALID TEAM LENGTH IN MATCH CLASS");
            System.exit(1);
        }
        Teams = new Team[teams.length];
        for(int i = 0; i < teams.length;i++)
        {
            Teams[i] = teams[i];
        }
    }
}
