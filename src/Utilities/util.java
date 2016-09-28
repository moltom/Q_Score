package Utilities;

public class util
{
    public static int parse(String in)
    {
        try{
            return Integer.parseInt(in);
        }
        catch(NumberFormatException e)
        {
            return 0;
        }
    }

    public static String boolToString(boolean in)
    {
        return ""+in;
    }
}
