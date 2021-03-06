package ohtu;

public class Player {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAsists() {
        return assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public int getScore() {
        return goals + assists;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGames() {
        return games;
    }

    @Override
    public String toString() {
        return
            name + " (" + team + ") " + nationality + " | " +
            goals + " + " + assists + " = " + getScore() +
            " | penalties " + penalties +
            " | games " + games;
    }
}
