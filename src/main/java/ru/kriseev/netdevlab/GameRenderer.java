package ru.kriseev.netdevlab;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import ru.kriseev.netdevlab.model.Arrow;
import ru.kriseev.netdevlab.model.GameState;
import ru.kriseev.netdevlab.model.Player;
import ru.kriseev.netdevlab.model.Target;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer {

    private final Pane gamePane;


    public GameRenderer(Pane gamePane) {
        this.gamePane = gamePane;
    }

    private Polygon createPlayerPolygon(Player player) {
        Polygon poly = new Polygon();
        poly.getPoints().addAll(player.getX(), player.getY(), player.getX() - 20, player.getY() - 20,
                player.getX() - 20, player.getY() - 20);
        poly.setFill(Color.GREEN);
        return poly;
    }
    private void renderTargets(Target[] targets, List<Node> nodes) {
        for(Target t : targets) {
            Circle c = new Circle();
            c.setCenterX(t.getX());
            c.setCenterY(t.getY());
            c.setRadius(t.getRadius());
            c.setFill(Color.RED);
            nodes.add(c);
        }
    }
    private void renderPlayers(Player[] players, List<Node> nodes) {
        for(Player p : players) {
            nodes.add(createPlayerPolygon(p));
            Label playerName = new Label(p.getNickname());
            playerName.setLayoutX(p.getX() - 20.0);
            playerName.setLayoutY(p.getY() - 35.0);
            nodes.add(playerName);
            if(p.getArrow() != null) {
                nodes.add(renderArrow(p.getArrow()));
            }
        }
    }

    private Node renderArrow(Arrow arrow) {
        Line line = new Line();
        line.setStroke(Color.GREEN);
        line.setStartX(arrow.getX());
        line.setStartY(arrow.getY());
        line.setEndX(arrow.getX() - 50.0);
        line.setEndY(arrow.getY());
        return line;
    }

    public void renderState(GameState state) {
        if(state == null) {
            return;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        renderTargets(state.getTargets(), nodes);
        renderPlayers(state.getPlayers(), nodes);
        gamePane.getChildren().clear();
        gamePane.getChildren().addAll(nodes);
    }
    public void clearGame() {
        gamePane.getChildren().clear();
    }
}
