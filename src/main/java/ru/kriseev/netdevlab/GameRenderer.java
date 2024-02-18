package ru.kriseev.netdevlab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import ru.kriseev.netdevlab.model.Game;
import ru.kriseev.netdevlab.model.Target;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameRenderer {
    private Pane gamePane;
    private Polygon player;

    private Line arrow;

    private List<Circle> targets;

    private final Game game;

    public GameRenderer(Pane gamePane, Game game) {
        this.gamePane = gamePane;
        this.game = game;
    }

    public void createGame() {
        synchronized (game) {
            player = new Polygon();
            player.getPoints().addAll(game.getPlayer().getX(), game.getPlayer().getY(),
                    game.getPlayer().getX() - 20, game.getPlayer().getY() - 20,
                    game.getPlayer().getX() - 20, game.getPlayer().getY() + 20);
            player.setFill(Color.GREEN);
            targets = Arrays.stream(game.getTargets()).map((t) -> new Circle(t.getX(), t.getY(), t.getRadius(), Color.RED)).collect(Collectors.toList());
            arrow = new Line(game.getPlayer().getX(), game.getPlayer().getY(), game.getPlayer().getX() - 50, game.getPlayer().getY());
            arrow.setVisible(false);
        }
        gamePane.getChildren().add(player);
        gamePane.getChildren().add(arrow);
        gamePane.getChildren().addAll(targets);
    }
    public void clearGame() {
        gamePane.getChildren().clear();
    }

    public void update() {
        synchronized (game) {
            if(game.getArrow() != null) {
                arrow.setStartX(game.getArrow().getX());
                arrow.setEndX(game.getArrow().getX() - 50);
                arrow.setVisible(true);
            }
            else {
                arrow.setVisible(false);
            }

            for(int i = 0; i < game.getTargets().length; ++i) {
                targets.get(i).setCenterY(game.getTargets()[i].getY());
            }
        }
    }
}
