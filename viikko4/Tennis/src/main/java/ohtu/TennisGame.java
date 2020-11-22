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

    private String sameScore() {
        switch (player1Score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
        }
        return "Deuce";
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
        String score = "";
        int tempScore=0;

        if (player1Score==player2Score) {
            return sameScore();
        }

        if (player1Score >= 4 || player2Score >= 4) {
            return advantageScore();
        }

        for (int i=1; i<3; i++)
        {
            if (i==1) tempScore = player1Score;
            else { score+="-"; tempScore = player2Score;}
            switch(tempScore)
            {
                case 0:
                    score+="Love";
                    break;
                case 1:
                    score+="Fifteen";
                    break;
                case 2:
                    score+="Thirty";
                    break;
                case 3:
                    score+="Forty";
                    break;
            }
        }

        return score;
    }
}
