package ru.kriseev.netdevlab.model;

public class Game {
    private int score;
    private int shotsCount;
    private Target[] targets;
    private Arrow arrow = null;
    private Player player;
    private double fieldWidth;
    private double fieldHeight;

    public double getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(double fieldWidth) {
        this.fieldWidth = fieldWidth;
        player = new Player(fieldWidth * 0.1, fieldHeight * 0.5);
    }

    public double getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(double fieldHeight) {
        this.fieldHeight = fieldHeight;
        player = new Player(fieldWidth * 0.1, fieldHeight * 0.5);
    }

    public int getScore() {
        return score;
    }

    public Target[] getTargets() {
        return targets;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public int getShotsCount() {
        return shotsCount;
    }

    public Game(double fieldWidth, double fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        score = 0;
        shotsCount = 0;
        targets = new Target[] {
                new Target(20, fieldWidth * 0.66, fieldHeight / 2, fieldHeight - 30, 30, 1, 1),
                new Target(10, fieldWidth * 0.9, fieldHeight / 2, fieldHeight - 30, 30, 3, 1),
        };
        player = new Player(fieldWidth * 0.1, fieldHeight * 0.5);
    }
    public void reset() {
        targets = new Target[] {
                new Target(20, fieldWidth * 0.66, fieldHeight * 0.5, fieldHeight - 30, 30, 1, 1),
                new Target(10, fieldWidth * 0.9, fieldHeight * 0.5, fieldHeight - 30, 30, 3, 1),
        };
        score = 0;
        shotsCount = 0;
        arrow = null;
    }
    public void step() {
        if(arrow != null) {
            arrow.step();
            if(arrow.getX() > fieldWidth) {
                arrow = null;
            }
        }
        for(Target target : targets) {
            target.step();
            if(arrow != null && target.checkHit(arrow)) {
                score += target.getScoreIncrement();
                arrow = null;
            }
        }
        
    }
    public void shoot() {
        if(arrow != null) {
            return;
        }
        arrow = new Arrow(player.getX(), player.getY(), 3);
        shotsCount++;
    }
    public void killArrow() {
        arrow = null;
    }

    public Player getPlayer() {
        return player;
    }
}
