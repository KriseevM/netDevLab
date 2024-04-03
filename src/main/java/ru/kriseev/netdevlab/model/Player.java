package ru.kriseev.netdevlab.model;

public class Player {
    private final String nickname;
    private double x;
    private double y;
    private int score = 0;
    private int shotsCount = 0;
    private Boolean ready = false;

    private Arrow arrow;

    public Player(double x, double y, String nickname) {
        this.x = x;
        this.y = y;
        this.nickname = nickname;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public int getScore() {
        return score;
    }

    public int getShotsCount() {
        return shotsCount;
    }

    public double getY() {
        return y;
    }

    public void incrementShots() {
        shotsCount++;
    }

    public void incrementScore(int increment) {
        score += increment;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
    public void shoot() {
        if(arrow != null) {
            return;
        }
        arrow = new Arrow(x, y, 6);
        incrementShots();
    }

    public Boolean isReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public String getNickname() {
        return nickname;
    }
    public void reset() {
        score = 0;
        shotsCount = 0;
    }
}
