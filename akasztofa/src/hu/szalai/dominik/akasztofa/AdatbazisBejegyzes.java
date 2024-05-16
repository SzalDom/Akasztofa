package hu.szalai.dominik.akasztofa;

public class AdatbazisBejegyzes {
    private String username;
    private int score;
    private String timestamp;

    public AdatbazisBejegyzes(String username, int score, String timestamp) {
        this.username = username;
        this.score = score;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return username + " - " + score + " - " + timestamp;
    }
}

