package ru.kriseev.netdevlab.model;

public class GameState {
    public GameState(double fieldWidth, double fieldHeight, Target[] targets, int playerCount)
    {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        players = new Player[playerCount];
        this.targets = targets;
        this.isFinished = false;
        winner = null;
        paused = false;
    }
    public GameState(double fieldWidth, double fieldHeight, Target[] targets, Player[] players) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.players = players;
        this.targets = targets;
        isFinished = false;
        winner = null;
        paused = false;
    }
    public GameState(double fieldWidth, double fieldHeight, Target[] targets, Player[] players, Boolean isFinished, String winner, Boolean paused) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.players = players;
        this.targets = targets;
        this.isFinished = isFinished;
        this.winner = winner;
        this.paused = paused;
    }
    private final Target[] targets;
    private final Player[] players;
    private final double fieldWidth;
    private final double fieldHeight;

    private String winner;

    private Boolean isFinished;

    private Boolean paused;

    public void setIsFinished(Boolean value) {
        isFinished = value;
    }
    public Boolean getIsFinished() {
        return isFinished;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getWinner() {
        return winner;
    }
    public Target[] getTargets() {
        return targets;
    }

    public Player[] getPlayers() {
        return players;
    }

    public double getFieldWidth() {
        return fieldWidth;
    }

    public double getFieldHeight() {
        return fieldHeight;
    }
    public Boolean getPaused() {
        return paused;
    }
    public void setPaused(Boolean paused) {
        this.paused = paused;
    }
}
