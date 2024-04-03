package ru.kriseev.netdevlab.model;

public class Arrow {
    private double y;
    private double x;
    private final double speed;
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void step() {
        x += speed;
    }

    public Arrow(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
}
