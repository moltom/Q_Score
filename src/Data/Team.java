package Data;

public class Team
{
    private int team_number;

    private int highParticle;
    private int lowParticle;
    private int beacon;
    private boolean capLow, capCross, capHigh;
    private boolean parkPartial, parkFull;
    private boolean autoCap;
    private int points;
    private int autoHighParticle, autoLowParticle, autoBeacons;

    public Team()
    {
        reset();
    }

    public void reset()
    {
        team_number = 0;
        highParticle = 0;
        lowParticle = 0;
        beacon = 0;
        capLow = false;
        capCross = false;
        capHigh = false;
        parkPartial = false;
        parkFull = false;
        points = 0;
        autoHighParticle = 0;
        autoLowParticle = 0;
        autoBeacons = 0;
        autoCap = false;
    }

    public String toString()
    {
        String out = "";
        out += (autoHighParticle + ",");
        out += (highParticle + ",");
        out += (autoLowParticle + ",");
        out += (lowParticle + ",");
        out += (autoBeacons + ",");
        out += (beacon + ",");
        out += (autoCap + ",");
        out += (capLow + ",");
        out += (capCross + ",");
        out += (capHigh + ",");
        out += (parkPartial + ",");
        out += (parkFull + ",");
        out += (points + ",");

        return out;
    }

    public int getTeam_number() {
        return team_number;
    }

    public void setTeam_number(int team_number) {
        this.team_number = team_number;
    }

    public int getHighParticle() {
        return highParticle;
    }

    public void setHighParticle(int highParticle) {
        this.highParticle = highParticle;
    }

    public int getLowParticle() {
        return lowParticle;
    }

    public void setLowParticle(int lowParticle) {
        this.lowParticle = lowParticle;
    }

    public int getBeacon() {
        return beacon;
    }

    public void setBeacon(int beacon) {
        this.beacon = beacon;
    }

    public boolean isCapLow() {
        return capLow;
    }

    public void setCapLow(boolean capLow) {
        this.capLow = capLow;
    }

    public boolean isCapCross() {
        return capCross;
    }

    public void setCapCross(boolean capCross) {
        this.capCross = capCross;
    }

    public boolean isCapHigh() {
        return capHigh;
    }

    public void setCapHigh(boolean capHigh) {
        this.capHigh = capHigh;
    }

    public boolean isParkPartial() {
        return parkPartial;
    }

    public void setParkPartial(boolean parkPartial) {
        this.parkPartial = parkPartial;
    }

    public boolean isParkFull() {
        return parkFull;
    }

    public void setParkFull(boolean parkFull) {
        this.parkFull = parkFull;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAutoHighParticle() {
        return autoHighParticle;
    }

    public void setAutoHighParticle(int autoHighParticle) {
        this.autoHighParticle = autoHighParticle;
    }

    public int getAutoLowParticle() {
        return autoLowParticle;
    }

    public void setAutoLowParticle(int autoLowParticle) {
        this.autoLowParticle = autoLowParticle;
    }

    public int getAutoBeacons() {
        return autoBeacons;
    }

    public void setAutoBeacons(int autoBeacons) {
        this.autoBeacons = autoBeacons;
    }

    public boolean isAutoCap() {
        return autoCap;
    }

    public void setAutoCap(boolean autoCap) {
        this.autoCap = autoCap;
    }
}
