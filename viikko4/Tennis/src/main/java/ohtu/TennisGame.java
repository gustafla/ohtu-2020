package ohtu;

public class TennisGame {
    private int player1Score = 0;
    private int player2Score = 0;

    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score += 1;
        else if (playerName.equals(player2Name)) {
            player2Score += 1;
        } else {
            throw new IllegalArgumentException("Player name was not recognized");
        }
    }

    private String scoreCall(int score) {
        switch(score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return "Game";
    }

    private String sameScore() {
        if (player1Score > 3) {
            return "Deuce";
        }

        return scoreCall(player1Score) + "-All";
    }

    private String advantageScore() {
        int difference = player1Score - player2Score;

        String name = player1Name;
        if (difference < 0) {
            name = player2Name;
        }

        if (Math.abs(difference) == 1) {
            return "Advantage " + name;
        }

        return "Win for " + name;
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return sameScore();
        }

        if (player1Score >= 4 || player2Score >= 4) {
            return advantageScore();
        }

        return scoreCall(player1Score) + "-" + scoreCall(player2Score);
    }
}
