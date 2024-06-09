package DataBase;

public class FIFADataBase {
    private String playerId;
    private String playerName;
    private String playerNationality;
    private String playerClub;
    private Byte playerOverall;

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerNationality(String playerNationality) {
        this.playerNationality = playerNationality;
    }

    public void setPlayerClub(String playerClub) {
        this.playerClub = playerClub;
    }

    public void setPlayerOverall(Byte playerOverall) {
        this.playerOverall = playerOverall;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getPlayerNationality() {
        return this.playerNationality;
    }

    public String getPlayerClub() {
        return this.playerClub;
    }

    public Byte getPlayerOverall() {
        return this.playerOverall;
    }

    public FIFADataBase() {
    }

    public FIFADataBase(String playerId, String playerName, String playerNationality,
            String playerClub, Byte playerOverall) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerNationality = playerNationality;
        this.playerClub = playerClub;
        this.playerOverall = playerOverall;

    }

    public String toString() {
        return "Player ID: " + playerId + " | Player name: " + playerName + " | Player nationality: "
                + playerNationality +
                " | Player club: " + playerClub + " | Player overall: " + playerOverall;
    }
}